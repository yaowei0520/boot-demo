package com.yw.exception.net;

import com.yw.code.base.IErrorCode;
import com.yw.exception.CommonException;

/**
 * RPC异常类型
 * 
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class RPCException extends CommonException {

    public RPCException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public RPCException(IErrorCode errorCodeObj, Throwable cause) {
        super(errorCodeObj, cause);
    }

    public RPCException(String errorCode, String message) {
        super(errorCode, message);
    }

    public RPCException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
