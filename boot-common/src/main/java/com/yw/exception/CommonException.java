package com.yw.exception;


import com.yw.code.base.IErrorCode;

/**
 * 整个系统异常(检查性异常)
 * 
 * @author admin
 *
 */
public class CommonException extends Exception {
  private static final long serialVersionUID = 1L;
  protected final String code;
  protected final String msg;
  protected IErrorCode errorCodeObj;

  public CommonException(IErrorCode errorCodeObj, Throwable cause) {
    super(errorCodeObj.getErrorCode() + ":" + errorCodeObj.getErrorMsg(), cause);
    this.errorCodeObj = errorCodeObj;
    this.code = errorCodeObj.getErrorCode();
    this.msg = errorCodeObj.getErrorMsg();
  }

  public CommonException(IErrorCode errorCodeObj) {
    super(errorCodeObj.getErrorCode() + ":" + errorCodeObj.getErrorMsg());
    this.errorCodeObj = errorCodeObj;
    this.code = errorCodeObj.getErrorCode();
    this.msg = errorCodeObj.getErrorMsg();
  }

  /**
   * 该逻辑兼容不做code映射的系统，不建议使用
   * 
   * @param code
   * @param msg
   */
  public CommonException(String code, String msg) {
    super(code + ":" + msg);
    this.code = code;
    this.msg = msg;
  }

  /**
   * 该逻辑兼容不做code映射的系统，不建议使用
   * 
   * @param code
   * @param msg
   * @param cause
   */
  public CommonException(String code, String msg, Throwable cause) {
    super(code + ":" + msg, cause);
    this.code = code;
    this.msg = msg;
  }

  /**
   * Getter method for property <tt>code</tt>.
   * 
   * @return property value of code
   */
  public String getCode() {
    return code;
  }

  /**
   * Getter method for property <tt>msg</tt>.
   * 
   * @return property value of msg
   */
  public String getMsg() {
    return msg;
  }

  /**
   * Getter method for property <tt>errorCodeObj</tt>.
   * 
   * @return property value of errorCodeObj
   */
  public IErrorCode getErrorCodeObj() {
    return errorCodeObj;
  }

}
