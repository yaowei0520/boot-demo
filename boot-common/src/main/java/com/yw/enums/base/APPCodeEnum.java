package com.yw.enums.base;

/**
 * 系统编码定义
 * 
 */
public enum APPCodeEnum {
    // base-platform
    KPBF_FRAMEWORK("201", "公共框架"),

    KPBF_STRUCTRUE("202", "基础框架"),

    KPBF_REGISTER("203", "服务注册中心"),

    ;

    public String sysId;
    public String sysDesc;

    private APPCodeEnum(String sysId, String sysDesc) {
        this.sysId = sysId;
        this.sysDesc = sysDesc;
    }
}
