package com.yw.exception.business;


import com.yw.code.base.IErrorCode;
import com.yw.exception.CommonException;

/**
 * 业务层异常
 * 
 * @author admin
 *
 */
public class BusinessException extends CommonException {

    private static final long serialVersionUID = 1L;

    public BusinessException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public BusinessException(IErrorCode errorCodeObj, Throwable cause) {
        super(errorCodeObj, cause);
    }

    public BusinessException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
