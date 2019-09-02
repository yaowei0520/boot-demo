package com.yw.log;

import org.slf4j.LoggerFactory;

public class LoggerAdapterFactory {

    private LoggerAdapterFactory() {

    }

    public static LoggerAdapter getLogger(String name) {
        return new LoggerAdapter(LoggerFactory.getLogger(name));
    }
}
