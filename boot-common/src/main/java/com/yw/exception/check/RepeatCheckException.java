package com.yw.exception.check;


import com.yw.code.base.IErrorCode;

/**
 * 数据重复异常
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class RepeatCheckException extends CheckException {

    public RepeatCheckException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public RepeatCheckException(String errorCode, String message) {
        super(errorCode, message);
    }

    public RepeatCheckException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
