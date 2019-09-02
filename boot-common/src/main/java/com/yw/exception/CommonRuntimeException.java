package com.yw.exception;


import com.yw.code.base.IErrorCode;

/**
 * 系统异常(检查性异常)
 * 
 * @author admin
 * @version $Id: CommonRuntimeException.java, v 0.1 2017年6月1日 下午4:06:49 admin
 *          Exp $
 */
public class CommonRuntimeException extends KPException {
    private static final long serialVersionUID = 1L;
    protected IErrorCode errorCodeObj;

    public CommonRuntimeException(IErrorCode errorCodeObj, Throwable cause) {
        super(errorCodeObj.getErrorCode(), errorCodeObj.getErrorMsg(), cause);
        this.errorCodeObj = errorCodeObj;
    }

    public CommonRuntimeException(IErrorCode errorCodeObj) {
        super(errorCodeObj.getErrorCode(), errorCodeObj.getErrorMsg());
        this.errorCodeObj = errorCodeObj;
    }

    /**
     * 该逻辑兼容不做code映射的系统，不建议使用
     * 
     * @param code
     * @param msg
     */
    public CommonRuntimeException(String code, String msg) {
        super(code, msg);
    }

    /**
     * 该逻辑兼容不做code映射的系统，不建议使用
     * 
     * @param code
     * @param msg
     * @param cause
     */
    public CommonRuntimeException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    /**
     * Getter method for property <tt>errorCodeObj</tt>.
     * 
     * @return property value of errorCodeObj
     */
    public IErrorCode getErrorCodeObj() {
        return errorCodeObj;
    }

    /**
     * Setter method for property <tt>errorCodeObj</tt>.
     * 
     * @param errorCodeObj
     *            value to be assigned to property errorCodeObj
     */
    public void setErrorCodeObj(IErrorCode errorCodeObj) {
        this.errorCodeObj = errorCodeObj;
    }

}
