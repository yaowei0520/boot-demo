package com.yw.exception;

import com.yw.enums.base.KPErrorEnum;
import org.apache.commons.lang3.StringUtils;


/**
 * 远端服务调用异常
 * 
 * @author fuli
 * @version 1.0
 * @date 2017-03-22 16:23
 */
public class RemoteException extends KPException {
    private static final long serialVersionUID = 1L;
    private String thirdCode;
    private String thirdMsg;

    public RemoteException() {
        super();
    }

    public RemoteException(int httpStatus) {
        super(httpStatus);
    }

    public RemoteException(String code) {
        super(code);
    }

    public RemoteException(String code, int httpStatus) {
        super(code, httpStatus);
    }

    public RemoteException(String code, String message) {
        super(code, message);
    }

    public RemoteException(String code, String message, int httpStatus) {
        super(code, message, httpStatus);
    }

    public RemoteException(KPErrorEnum kpei) {
        super(kpei);
    }

    public RemoteException(KPErrorEnum kpei, int httpStatus) {
        super(kpei, httpStatus);
    }

    public RemoteException(String code, Exception ex) {
        super(code, ex);
    }

    public RemoteException(KPErrorEnum kpei, Throwable ex) {
        super(kpei, ex);
    }

    public RemoteException(KPErrorEnum kpei, int httpStatus, Throwable ex) {
        super(kpei, ex);
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getThirdMsg() {
        return thirdMsg;
    }

    public void setThirdMsg(String thirdMsg) {
        this.thirdMsg = thirdMsg;
    }

    @Override
    public String getMessage() {
        StringBuilder temp = new StringBuilder(StringUtils.defaultString(super.getMessage()));
        if (StringUtils.isNotBlank(thirdMsg)) {
            temp.append("[").append(thirdMsg);
            if (StringUtils.isNotBlank(thirdCode)) {
                temp.append("(").append(thirdCode).append(")");
            }
            temp.append("]");
        }
        return temp.toString();
    }
}
