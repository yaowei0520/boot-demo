package com.yw.exception;


import com.yw.enums.base.KPErrorEnum;

/**
 * KunPu异常信息封装
 * 
 */
public class KPException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String INFO = "Kunpu Business Error";
    private int httpStatus = 200;
    private final String code;
    private String msg;
    private Object addiData;

    public KPException() {
        super(INFO);
        this.code = KPErrorEnum.SYSTEM_ERR.CODE;
        this.msg = KPErrorEnum.SYSTEM_ERR.MESSAGE;
    }

    public KPException(int httpStatus) {
        super(INFO);
        this.httpStatus = httpStatus;
        this.code = KPErrorEnum.SYSTEM_ERR.CODE;
        this.msg = KPErrorEnum.SYSTEM_ERR.MESSAGE;
    }

    public KPException(String code) {
        super(INFO);
        this.code = code;
        this.msg = INFO;
    }

    public KPException(String code, int httpStatus) {
        super(INFO);
        this.code = code;
        this.httpStatus = httpStatus;
        this.msg = INFO;
    }

    public KPException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public KPException(String code, String msg, int httpStatus) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public KPException(KPErrorEnum kpei) {
        super(kpei.MESSAGE);
        this.code = kpei.CODE;
        this.msg = kpei.MESSAGE;
    }

    public KPException(KPErrorEnum kpei, int httpStatus) {
        super(kpei.MESSAGE);
        this.code = kpei.CODE;
        this.msg = kpei.MESSAGE;
        this.httpStatus = httpStatus;
    }

    public KPException(String code, Throwable ex) {
        super(code, ex);
        this.code = code;
        this.msg = ex.getMessage();
    }

    public KPException(KPErrorEnum kpei, Throwable ex) {
        super(kpei.MESSAGE, ex);
        this.code = kpei.CODE;
        this.msg = kpei.MESSAGE;
    }

    public KPException(KPErrorEnum kpei, int httpStatus, Throwable ex) {
        super(kpei.MESSAGE, ex);
        this.code = kpei.CODE;
        this.msg = kpei.MESSAGE;
        this.httpStatus = httpStatus;
    }

    public KPException(String code, String msg, Throwable ex) {
        super(code, ex);
        this.code = code;
        this.msg = msg;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getAddiData() {
        return addiData;
    }

    public void setAddiData(Object addiData) {
        this.addiData = addiData;
    }

    @Override
    public String getMessage() {
        return msg + "(" + code + ")";
    }

}
