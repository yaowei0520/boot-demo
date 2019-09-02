package com.yw.code.base;

public interface IRespCode {

    public String getRespCode();

    public String getRespMsg();

    public IRespCode getCurrentCodeObj();

    public IRespCode getCurrentObjByCode(String code);

}
