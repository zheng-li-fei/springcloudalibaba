package com.zlf.gatewayshop.enums.error;


import com.zlf.commonbase.constant.IErrorInfo;

/**
 * @description: 网关错误枚举
 * @author: zhenglifei
 * @create: 2022/11/10 15:23
 **/
public enum GatewayErrorEnum implements IErrorInfo {

    /**
     * 网关错误枚举
     */
    GATEWAY_ILLEGAL_TOKEN(91209100, "无效的token访问"),
    GATEWAY_LOGIN_EXPIRE(91209101, "用户登录过期，请重新登录"),
    ;

    private final int code;
    private final String message;

    GatewayErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "[" + code + "]" + message;
    }
}
