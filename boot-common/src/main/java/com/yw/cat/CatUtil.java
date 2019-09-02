package com.yw.cat;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

public class CatUtil {
    private static AtomicBoolean isEnable = new AtomicBoolean(false);

    private CatUtil() {
    }

    public static boolean isCatEnable() {
        return isEnable.get();
    }

    public static void setCatEnable(boolean value) {
        isEnable.set(value);
    }

    public static boolean isDubboException(Throwable e) {
        String name = e.getClass().getPackage().getName();
        if (name.startsWith("com.alibaba.dubbo")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * DB异常已在插件中监控
     * 
     * @param e
     * @return
     */
    public static boolean isDBException(Throwable e) {
        if (e instanceof RuntimeException) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSystemException(Throwable e) {
        String name = e.getClass().getPackage().getName();
        // 过滤所有平安付自定义的异常
        //com.kunpu.framework.commons.utils.exception>>com.kunpu.frameworks.commons.exception
        if (name.startsWith("com.kunpu.framework.commons.utils.exception")) {
             return true;
        } else if (name.startsWith("com.kunpu.frameworks.commons.exception")) {
             return true;
        } else if (name.startsWith("com.kunpu")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * cat异常监控
     * 
     * @param e
     */
    public static void traceSystemException(Throwable e) {
        if (!CatUtil.isCatEnable()) {
            return;
        }
        if (CatUtil.isDBException(e)) {
            return;
        }
        if (CatUtil.isDubboException(e)) {
            Cat.getProducer().logEvent("Dubbo Exception error", e.getMessage());
        } else if (CatUtil.isSystemException(e)) {
            Cat.getProducer().logError(e);

            if (StringUtils.isBlank(e.getMessage())) {
                return;
            }
            String errMsg = "";
            if (e.getMessage().length() > 6) {
                errMsg = e.getMessage().substring(0, 6);
            } else {
                errMsg = e.getMessage();
            }

            Cat.logEvent("EXCEPTION_STATISTICS", errMsg);
        }
    }

    /**
     * 更新cat事务状态
     * 
     * @param transaction
     * @param status
     */
    public static void setTransactionStatus(Transaction transaction, String status) {
        transaction.setStatus(status);
        transaction.complete();
    }

    /**
     * 更新cat事务异常状态
     * 
     * @param transaction
     * @param e
     */
    public static void setTransactionExceptionStatus(Transaction transaction, Throwable e) {
        transaction.setStatus(e);
        transaction.complete();
    }

    public static void logError(Throwable cause) {
        if (cause instanceof Error)
            Cat.logEvent("Error", cause.getClass().getSimpleName(), "ERROR", cause.getMessage());
        else if (cause instanceof RuntimeException)
            Cat.logEvent("RuntimeException", cause.getClass().getSimpleName(), "ERROR", cause.getMessage());
        else
            Cat.logEvent("Exception", cause.getClass().getSimpleName(), "ERROR", cause.getMessage());
    }
}
