package com.yw.exception.check;


import com.yw.code.base.IErrorCode;

/**
 * 商户访问权限异常
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class AccessRuleCheckException extends CheckException {

    public AccessRuleCheckException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public AccessRuleCheckException(String errorCode, String message) {
        super(errorCode, message);
    }

    public AccessRuleCheckException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
