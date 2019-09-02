package com.yw.code.base;

import java.util.HashMap;
import java.util.Map;

public interface BaseRespCode {
    /**
     * 系统错误相应码
     * 
     */
    public static Map<String, String> SYS_ERROR_RESP_MAP = new HashMap<String, String>();
    /**
     * 可重试错误码
     */
    public static Map<String, String> SYS_RETRY_RESP_MAP = new HashMap<String, String>();
    /**
     * 位置响应吗
     */
    public static Map<String, String> SYS_UNKNOW_RESP_MAP = new HashMap<String, String>();

}
