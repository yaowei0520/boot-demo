package com.yw.exception.handle;

import com.yw.enums.base.APPCodeEnum;
import com.yw.exception.CommonException;
import com.yw.exception.CommonRuntimeException;
import com.yw.exception.biz.BizException;
import org.apache.commons.lang3.StringUtils;


/**
 * 内部错误异常转换Biz异常
 * 
 * @author admin
 *
 */
public class BizExceptionHandler {

  private BizExceptionHandler() {

  }

  /**
   * 
   * 
   * @param e
   * @throws BizException
   */
  public static void commonExceptionHandle(APPCodeEnum appCode, CommonException e)
      throws BizException {
    commonHandle(appCode, e);
  }

  /**
   * 
   * 
   * @param e
   * @throws BizException
   */
  public static void commonRuntimeExceptionHandle(APPCodeEnum appCode, CommonRuntimeException e)
      throws BizException {
    commonRuntimeException(appCode, e);
  }

  /**
   * 
   * 
   * @param e
   * @throws BizException
   */
  private static void commonRuntimeException(APPCodeEnum appCode, CommonRuntimeException e)
      throws BizException {
    if (e.getErrorCodeObj() != null && e.getErrorCodeObj().getRespCodeObj() != null) {
      throw new BizException(appCode.sysId + e.getErrorCodeObj().getRespCodeObj().getRespCode(), e
          .getErrorCodeObj().getRespCodeObj().getRespMsg(), e);
    } else {
      String responseCode = "";
      if (StringUtils.isNotBlank(e.getCode()) && e.getCode().length() == 6) {
        responseCode = e.getCode();
      } else {
        responseCode = appCode.sysId + e.getCode();
      }
      throw new BizException(responseCode, e.getMsg() + " 错误信息:[" + e.getMessage() + "]", e);
    }
  }

  /**
   * 
   * 
   * @param e
   * @throws BizException
   */
  private static void commonHandle(APPCodeEnum appCode, CommonException e) throws BizException {
    if (e.getErrorCodeObj() != null && e.getErrorCodeObj().getRespCodeObj() != null) {
      throw new BizException(appCode.sysId + e.getErrorCodeObj().getRespCodeObj().getRespCode(), e
          .getErrorCodeObj().getRespCodeObj().getRespMsg(), e);
    } else {
      String responseCode = "";
      if (StringUtils.isNotBlank(e.getCode()) && e.getCode().length() == 6) {
        responseCode = e.getCode();
      } else {
        responseCode = appCode.sysId + e.getCode();
      }
      throw new BizException(responseCode, e.getMsg() + " 错误信息:[" + e.getMessage() + "]", e);
    }
  }
}
