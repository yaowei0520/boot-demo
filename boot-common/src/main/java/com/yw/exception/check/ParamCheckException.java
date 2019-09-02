package com.yw.exception.check;


import com.yw.code.base.IErrorCode;

/**
 * 参数检查异常
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class ParamCheckException extends CheckException {

    public ParamCheckException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public ParamCheckException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ParamCheckException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
