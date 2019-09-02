package com.yw.exception.dao;


import com.yw.code.base.IErrorCode;
import com.yw.exception.CommonRuntimeException;

/**
 * 数据库异常
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class DBException extends CommonRuntimeException {

    public DBException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public DBException(IErrorCode errorCodeObj, Throwable cause) {
        super(errorCodeObj, cause);
    }

    public DBException(String errorCode, String message) {
        super(errorCode, message);
    }

    public DBException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
