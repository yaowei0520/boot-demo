package com.yw.code.base;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 系统内部异常code定义
 * 
 * @author admin
 *
 */
public enum CommonErrorCode implements IErrorCode {
    /** 成功 */
    COMMON_000000("000000", "成功", CommonRespCode.RESP_000000), //
    /** 未知原因失败 */
    COMMON_999("999", "未知原因失败", CommonRespCode.RESP_999), //

    /**
     * data check code 001-099
     */

    /** 入参数值不合法 */
    COMMON_001("001", "入参数值不合法", CommonRespCode.RESP_001), //
    /** 必要参数不能为空 */
    COMMON_002("002", "必要参数不能为空", CommonRespCode.RESP_001), //

    /**
     * bussiness check code 100-199
     */

    /** 无访问权限 */
    COMMON_100("100", "无访问权限", CommonRespCode.RESP_100), //

    /**
     * database code 200-299
     */
    /** 数据库操作失败 */
    COMMON_200("200", "数据库操作失败", CommonRespCode.RESP_200), //
    /** 数据库操作添加失败 */
    COMMON_201("201", "数据库操作添加失败", CommonRespCode.RESP_200), //
    /** 数据库操作修改失败 */
    COMMON_202("202", "数据库操作修改失败", CommonRespCode.RESP_200), //
    /** 数据库操作查询失败 */
    COMMON_203("203", "数据库操作查询失败", CommonRespCode.RESP_200), //
    /** 数据库操作删除失败 */
    COMMON_204("204", "数据库操作删除失败", CommonRespCode.RESP_200), //
    /** 数据库操作唯一索引约束冲突 */
    COMMON_205("205", "数据库操作唯一索引约束冲突", CommonRespCode.RESP_200), //

    /**
     * call remote service code 300-399
     */
    /** RPC URL不存在 */
    COMMON_300("300", "调用外部系统服务超时或异常", CommonRespCode.RESP_300), //
    /** RPC URL不存在 */
    COMMON_301("301", "RPC URL不存在", CommonRespCode.RESP_300), //
    /** RPC连接超时 */
    COMMON_302("302", "RPC连接超时", CommonRespCode.RESP_300), //
    /** 无权限访问服务 */
    COMMON_303("303", "无权限访问服务", CommonRespCode.RESP_300), //
    /** 服务方业务异常 */
    COMMON_304("304", "服务方业务异常", CommonRespCode.RESP_300), //
    /** RPC调用方法时响应超时 */
    COMMON_305("305", "RPC调用方法时响应超时", CommonRespCode.RESP_300), //
    /** 远程服务异常 */
    COMMON_306("306", "远程服务异常", CommonRespCode.RESP_300), //
    /** RPC网络异常 */
    COMMON_307("307", "RPC网络异常", CommonRespCode.RESP_300),
    /** RPC时序列化异常 */
    COMMON_308("308", "RPC时序列化异常", CommonRespCode.RESP_300),
    /** RPC参数错误 */
    COMMON_309("309", "RPC参数错误", CommonRespCode.RESP_300),
    /**
     * system code 400-499
     */
    /** 日期转型失败 */
    COMMON_400("400", "日期转型失败", CommonRespCode.RESP_400), //
    /** 对象转换失败 */
    COMMON_401("401", "对象转换失败", CommonRespCode.RESP_400), //
    /** 对象转换失败 */
    COMMON_402("402", "格式转换错误", CommonRespCode.RESP_400), //
    /** AES加密错误 */
    COMMON_403("403", "AES加密错误", CommonRespCode.RESP_400), //
    /** AES解密错误 */
    COMMON_404("404", "AES解密错误", CommonRespCode.RESP_400), //
    /** 身份证工具类校验错误 **/
    COMMON_405("405", "身份证工具类校验错误", CommonRespCode.RESP_400), //
    /** 配置项为NULL */
    COMMON_406("406", "配置项为NULL", CommonRespCode.RESP_400) //
    ;

    private String code;

    private String msg;

    private String extendMsg;

    private CommonRespCode commonRespCode;

    public static final List<String> RESULT_DETAIL_MSG_CODE_LIST = Arrays.asList(new String[] { "010001", "010002" });

    private CommonErrorCode(String code, String msg, CommonRespCode commonRespCode) {
        this.code = code;
        this.msg = msg;
        this.commonRespCode = commonRespCode;
    }

    public static CommonErrorCode getCommonErrorCode(String code) {
        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
            if (StringUtils.isNotBlank(code) && StringUtils.equals(code, errorCode.getCode())) {
                return errorCode;

            }
        }
        return null;
    }

    public String wrapperErrorMsg(String system, String errorMsg) {
        return this.getMsg() + "[异常系统:[" + system + "],异常信息:" + errorMsg + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Getter method for property <tt>CommonRespCode</tt>.
     * 
     * @return property value of CommonRespCode
     */
    public CommonRespCode getCommonRespCode() {
        return commonRespCode;
    }

    /**
     * Setter method for property <tt>CommonRespCode</tt>.
     * 
     * @param CommonRespCode
     *            value to be assigned to property CommonRespCode
     */
    public void setCommonRespCode(CommonRespCode commonRespCode) {
        this.commonRespCode = commonRespCode;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMsg() {
        return this.msg;
    }

    @Override
    public IRespCode getRespCodeObj() {
        return this.commonRespCode;
    }

    @Override
    public IErrorCode getCurrentCodeObj() {
        return this;
    }

    /**
     * Getter method for property <tt>extendMsg</tt>.
     * 
     * @return property value of extendMsg
     */
    public String getExtendMsg() {
        return extendMsg;
    }

    /**
     * Setter method for property <tt>extendMsg</tt>.
     * 
     * @param extendMsg
     *            value to be assigned to property extendMsg
     */
    public void setExtendMsg(String extendMsg) {
        this.extendMsg = extendMsg;
    }

    @Override
    public IErrorCode getCurrentObjByCode(String code) {
        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
            if (StringUtils.isNotBlank(code) && StringUtils.equals(code, errorCode.getCode())) {
                return errorCode;
            }
        }
        return null;
    }

}
