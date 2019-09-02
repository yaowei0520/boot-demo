package com.yw.enums.base;

/**
 * 系统信息定义<br/>
 * 根据需要各系统继承此类以扩展消息描述
 * 
 */
public class KPErrorEnum {

    public static final KPErrorEnum SUCCESS = new KPErrorEnum("000000", "SUCCESS");

    public static final KPErrorEnum NO_NULL = new KPErrorEnum("010000", "数据不能为空");

    public static final KPErrorEnum PARAM_TYPE_ERR = new KPErrorEnum("020000", "参数类型错误");

    public static final KPErrorEnum PARAM_FORMAT_ERR = new KPErrorEnum("030000", "参数格式错误");

    public static final KPErrorEnum NO_SUPPORT = new KPErrorEnum("040000", "不支持该操作");
    public static final KPErrorEnum NO_SUPPORT_PARAM = new KPErrorEnum("040001", "不支持该参数");

    public static final KPErrorEnum NO_POWER = new KPErrorEnum("050000", "无权限");
    public static final KPErrorEnum NO_LOGIN = new KPErrorEnum("050001", "未登录");
    
    public static final KPErrorEnum VALID_ERR = new KPErrorEnum("060000", "数据验证失败");

    
    
    public static final KPErrorEnum NOT_EXIST = new KPErrorEnum("999990", "数据不存在");
    public static final KPErrorEnum EXISTED = new KPErrorEnum("999991", "数据已存在");
    public static final KPErrorEnum ILLEGAL_OPT = new KPErrorEnum("999993", "非法操作");
    public static final KPErrorEnum HTTP_ERR = new KPErrorEnum("999997", "HTTP错误");
    public static final KPErrorEnum THIRD_ERR = new KPErrorEnum("999998", "第三方错误");
    public static final KPErrorEnum SYSTEM_ERR = new KPErrorEnum("999999", "系统错误");

    public String CODE;
    public String MESSAGE;
    protected KPErrorEnum(String CODE, String MESSAGE) {
        this.CODE = CODE;
        this.MESSAGE = MESSAGE;
    }
}
