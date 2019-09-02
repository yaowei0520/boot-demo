package com.yw.exception;

import com.yw.enums.base.KPErrorEnum;
import org.apache.commons.lang3.StringUtils;



/**
 * 本地业务异常
 * 
 */
public class LocalException extends KPException {
    private static final long serialVersionUID = 1L;
    private String field;

    public LocalException() {
        super();
    }

    public LocalException(int httpStatus) {
        super(httpStatus);
    }

    public LocalException(String code) {
        super(code);
    }

    public LocalException(String code, int httpStatus) {
        super(code, httpStatus);
    }

    public LocalException(String code, String message) {
        super(code, message);
    }

    public LocalException(String code, String message, int httpStatus) {
        super(code, message, httpStatus);
    }

    public LocalException(String field, String code, String message) {
        super(code, message);
        this.field = field;
    }

    public LocalException(String field, String code, String message, int httpStatus) {
        super(code, message, httpStatus);
        this.field = field;
    }

    public LocalException(KPErrorEnum kpei) {
        super(kpei);
    }

    public LocalException(KPErrorEnum kpei, int httpStatus) {
        super(kpei, httpStatus);
    }

    public LocalException(String field, KPErrorEnum kpei) {
        super(kpei);
        this.field = field;
    }

    public LocalException(String field, KPErrorEnum kpei, int httpStatus) {
        super(kpei, httpStatus);
        this.field = field;
    }

    public LocalException(String code, Exception ex) {
        super(code, ex);
    }

    public LocalException(String field, String code, Exception ex) {
        super(code, ex);
        this.field = field;
    }

    public LocalException(KPErrorEnum kpei, Throwable ex) {
        super(kpei, ex);
    }

    public LocalException(KPErrorEnum kpei, int httpStatus, Throwable ex) {
        super(kpei, httpStatus, ex);
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return StringUtils.defaultString(field) + super.getMessage();
    }
}
