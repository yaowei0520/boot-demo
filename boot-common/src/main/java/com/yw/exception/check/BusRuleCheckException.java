package com.yw.exception.check;


import com.yw.code.base.IErrorCode;

/**
 * 业务规则验证异常
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class BusRuleCheckException extends CheckException {

    public BusRuleCheckException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public BusRuleCheckException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusRuleCheckException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
