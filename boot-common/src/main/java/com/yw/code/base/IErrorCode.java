package com.yw.code.base;

public interface IErrorCode {

    public String getErrorCode();

    public String getErrorMsg();

    public IRespCode getRespCodeObj();

    public IErrorCode getCurrentCodeObj();

    public IErrorCode getCurrentObjByCode(String code);

}
