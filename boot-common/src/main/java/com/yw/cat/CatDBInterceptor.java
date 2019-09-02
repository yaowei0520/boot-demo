package com.yw.cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }),
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = ParameterHandler.class, method = "setParameters", args = { PreparedStatement.class }) })
public class CatDBInterceptor implements Interceptor {
    private static final ThreadLocal<Transaction> DB_TRANSACTION = new ThreadLocal<Transaction>();

    public Object intercept(Invocation invocation) throws Throwable {
        if (!CatUtil.isCatEnable())
            return invocation.proceed();
        BoundSql boundSql;
        if ((StatementHandler.class.isAssignableFrom(invocation.getTarget().getClass()))
                && ("prepare".equals(invocation.getMethod().getName()))) {
            StatementHandler handler = (StatementHandler) invocation.getTarget();
            boundSql = handler.getBoundSql();
            String sql = removeBreakingWhitespace(boundSql.getSql());
            String method = getSqlMethod(sql);
            Connection connection = (Connection) invocation.getArgs()[0];
            String url = connection.getMetaData().getURL();
            try {
                Transaction transaction = (Transaction) DB_TRANSACTION.get();
                if (transaction == null) {
                    transaction = Cat.newTransaction("SQL", method);
                    DB_TRANSACTION.set(transaction);
                }
                Event dbEvent = Cat.newEvent("SQL.Database", url);
                dbEvent.setStatus("0");
                dbEvent.complete();
                Event methodEvent = Cat.newEvent("SQL.Method", method);
                methodEvent.setStatus("0");
                methodEvent.complete();
                Event sqlEvent = Cat.newEvent("SQL.name", sql);
                sqlEvent.setStatus("0");
                sqlEvent.complete();
            } catch (Throwable e) {
            }

            try {
                return invocation.proceed();
            } catch (Throwable throwable) {
                CatUtil.logError(throwable);
                completeTx("DB-ERROR");
                DB_TRANSACTION.remove();
                throw throwable;
            }
        }
        if (Executor.class.isAssignableFrom(invocation.getTarget().getClass())) {
            try {
                Object object = invocation.proceed();
                completeTx("0");
                return object;
            } catch (Throwable throwable) {
                CatUtil.logError(throwable);
                completeTx("DB-ERROR");
                throw throwable;
            } finally {
                DB_TRANSACTION.remove();
            }
        }
        try {
            return invocation.proceed();
        } catch (Throwable e) {
            CatUtil.logError(e);
            completeTx("DB-ERROR");
            DB_TRANSACTION.remove();
            throw e;
        }
    }

    private void completeTx(String state) {
        if (DB_TRANSACTION.get() != null) {
            try {
                ((Transaction) DB_TRANSACTION.get()).setStatus(state);
                ((Transaction) DB_TRANSACTION.get()).complete();
            } catch (Throwable t) {
            }
        }
    }

    private String getSqlMethod(String sql) {
        int firstIndexOfBlank = sql.indexOf(" ");
        return sql.substring(0, firstIndexOfBlank);
    }

    private String removeBreakingWhitespace(String original) {
        StringTokenizer whitespaceStripper = new StringTokenizer(original);
        StringBuilder builder = new StringBuilder();

        while (whitespaceStripper.hasMoreTokens()) {
            builder.append(whitespaceStripper.nextToken());
            builder.append(" ");
        }

        return builder.toString();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}
