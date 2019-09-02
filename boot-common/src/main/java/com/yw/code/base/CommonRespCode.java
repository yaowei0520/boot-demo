package com.yw.code.base;

import org.apache.commons.lang3.StringUtils;

/**
 * 返回给外部系统的code memo
 * 
 * @author admin
 *
 */
public enum CommonRespCode implements IRespCode {
    /** 成功 */
    RESP_000000("000000", "成功"), //
    /** 未知原因失败 */
    RESP_999("999", "未知原因失败"), //

    /**
     * data check code 001-099
     */
    /** 参数不完整 */
    RESP_001("001", "参数错误"), //

    /**
     * bussiness check code 100-199
     */
    /** 无访问权限 */
    RESP_100("100", "无访问权限"), //
    /** 有重复请求数据 */
    RESP_101("101", "有重复请求数据"), //
    /**
     * database code 200-299
     */
    /** 数据库异常 */
    RESP_200("200", "数据库异常"), //
    /**
     * call remote service code 300-399
     */
    /** 调用外部系统服务超时或异常 */
    RESP_300("300", "调用外部系统服务超时或异常"), //
    /**
     * system code 400-499
     */
    /** 系统内部异常 */
    RESP_400("400", "系统内部异常"), //
    /**
     * 后续添加
     */
    ;

    private String code;

    private String desc;

    private CommonRespCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getRespCode() {
        return this.code;
    }

    @Override
    public String getRespMsg() {
        return this.desc;
    }

    @Override
    public IRespCode getCurrentCodeObj() {
        return this;
    }

    @Override
    public IRespCode getCurrentObjByCode(String code) {
        for (CommonRespCode respCode : CommonRespCode.values()) {
            if (StringUtils.isNotBlank(code) && StringUtils.equals(code, respCode.getCode())) {
                return respCode;
            }
        }
        return null;
    }

}
