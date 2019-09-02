package com.yw.exception.handle;

import com.yw.code.base.CommonErrorCode;
import com.yw.enums.base.APPCodeEnum;
import com.yw.exception.net.RPCException;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;


/**
 * RPC调用异常处理
 * 
 * @author admin
 *
 */
public class RPCExceptionHandler {

    private RPCExceptionHandler() {

    }

    /**
     * RPC异常包装
     * 
     * @param e
     * @throws RPCException
     */
    public static void rpcExceptionHandle(APPCodeEnum appCode, Exception e) throws RPCException {
        if (e instanceof FileNotFoundException) {
            throw new RPCException(CommonErrorCode.COMMON_301.getCode(),
                    CommonErrorCode.COMMON_301.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
        } else if (e instanceof ConnectException) {
            throw new RPCException(CommonErrorCode.COMMON_302.getCode(),
                    CommonErrorCode.COMMON_302.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
        } else if (e instanceof SocketTimeoutException) {
            throw new RPCException(CommonErrorCode.COMMON_305.getCode(),
                    CommonErrorCode.COMMON_305.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
        } /*else if (e instanceof RpcException) {
            RpcException rpcException = (RpcException) e;

            if (rpcException.isForbidded()) {
                throw new RPCException(CommonErrorCode.COMMON_303.getCode(),
                        CommonErrorCode.COMMON_303.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
            } else if (rpcException.isBiz()) {
                throw new RPCException(CommonErrorCode.COMMON_304.getCode(),
                        CommonErrorCode.COMMON_304.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
            } else if (rpcException.isNetwork()) {
                throw new RPCException(CommonErrorCode.COMMON_307.getCode(),
                        CommonErrorCode.COMMON_307.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
            } else if (rpcException.isSerialization()) {
                throw new RPCException(CommonErrorCode.COMMON_308.getCode(),
                        CommonErrorCode.COMMON_308.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
            } else if (rpcException.isTimeout()) {
                throw new RPCException(CommonErrorCode.COMMON_305.getCode(),
                        CommonErrorCode.COMMON_305.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
            }
        }*/
        throw new RPCException(appCode.sysId + CommonErrorCode.COMMON_300.getCode(),
                CommonErrorCode.COMMON_300.getMsg() + "[调用系统: " + appCode.sysDesc + "]", e);
    }
}
