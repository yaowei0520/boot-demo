package com.yw.log;

import com.yw.cat.CatUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class LoggerAdapter {
    private final Logger logger;

    public LoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void error(String msg, Throwable e) {
        logger.error(msg, e);
        CatUtil.traceSystemException(e);
    }

    public void dbError(String msg, Throwable e) {
        logger.error(msg, e);
    }

    public void dbError(String format, Object... arguments) {
        logger.error(format, siftException(arguments));
    }

    public void integrationError(String msg) {
        logger.error(msg);
    }

    public void integrationError(String code, String msg, Throwable e) {
        if (StringUtils.isNotBlank(code)) {
            logger.error(code + ":" + msg);
        } else {
            logger.error(msg);
        }
    }

    public void integrationError(String format, Object... arguments) {
        logger.error(format, siftException(arguments));
    }

    public void error(String format, Object... arguments) {
        logger.error(format, arguments);
        filterException(arguments);
    }

    public void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    private void filterException(Object[] args) {
        Object[] arrayOfObject;
        int j = (arrayOfObject = args).length;
        for (int i = 0; i < j; i++) {
            Object obj = arrayOfObject[i];
            if ((obj instanceof Throwable)) {
                CatUtil.traceSystemException((Throwable) obj);
            }
        }
    }

    private Object[] siftException(Object[] args) {
        List<Object> arguments = new ArrayList();
        Object[] arrayOfObject;
        int j = (arrayOfObject = args).length;
        for (int i = 0; i < j; i++) {
            Object obj = arrayOfObject[i];
            if (!(obj instanceof Throwable)) {
                arguments.add(obj);
            }
        }
        return arguments.toArray();
    }
}
