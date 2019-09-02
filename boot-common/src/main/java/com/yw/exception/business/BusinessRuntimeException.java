package com.yw.exception.business;


import com.yw.code.base.IErrorCode;
import com.yw.exception.CommonRuntimeException;

/**
 * 运行时异常-业务相关 主要包括 日期类型装换异常， 对象拷贝异常 计算异常，加密异常等
 * 
 * @author admin
 *
 */
public class BusinessRuntimeException extends CommonRuntimeException {

    private static final long serialVersionUID = -8589495766590812128L;

    public BusinessRuntimeException(IErrorCode errorCodeObj) {
        super(errorCodeObj);
    }

    public BusinessRuntimeException(IErrorCode errorCodeObj, Throwable cause) {
        super(errorCodeObj, cause);
    }

    public BusinessRuntimeException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessRuntimeException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
