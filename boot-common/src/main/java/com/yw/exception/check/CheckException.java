package com.yw.exception.check;


import com.yw.code.base.IErrorCode;
import com.yw.exception.CommonException;

/**
 * 检查异常
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class CheckException extends CommonException {

    public CheckException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public CheckException(IErrorCode errorCodeObj, Throwable cause) {
        super(errorCodeObj, cause);
    }

    public CheckException(String errorCode, String message) {
        super(errorCode, message);
    }

    public CheckException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
