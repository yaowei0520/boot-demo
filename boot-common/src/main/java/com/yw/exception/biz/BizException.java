package com.yw.exception.biz;


import com.yw.code.base.IErrorCode;
import com.yw.exception.CommonException;

/**
 * 
 * @author admin
 *
 */
public class BizException extends CommonException {

  private static final long serialVersionUID = -2531914859246081662L;

  public BizException(IErrorCode errorCodeObj) {
    super(errorCodeObj);
  }

  public BizException(IErrorCode errorCodeObj, Throwable cause) {
    super(errorCodeObj, cause);
  }

  public BizException(String errorCode, String message) {
    super(errorCode, message);
  }

  public BizException(String errorCode, String message, Throwable cause) {
    super(errorCode, message, cause);
  }
}
