package com.zlf.serverauth.enums.error;


import com.zlf.commonbase.constant.IErrorInfo;

/**
 * @description: 认证中心错误枚举
 * @author: zhenglifei
 * @create: 2022/11/10 15:23
 **/
public enum AuthErrorEnum implements IErrorInfo {

    /**
     * 认证中心错误枚举
     */
    SERVER_AUTH_ILLEGAL_LOGIN_TYPE(90909100, "非法登录类型"),
    SERVER_AUTH_ILLEGAL_PLATFORM_TYPE(90909101, "非法来源登录平台"),
    SERVER_AUTH_TYPE_ERROR(90909104, "type非法"),
    SERVER_AUTH_BEAN_NOT_FOUND(90909105, "bean 找不到"),

    SERVER_AUTH_USER_EXIST(90909120, "用户名已存在"),
    SERVER_AUTH_PHONE_EXIST(90909121, "手机号已被注册已存在"),
    SERVER_AUTH_USER_OR_PWD_ERROR(90909123, "用户名或密码错误"),
    SERVER_AUTH_DECODE_PWD_ERROR(90909124, "密码解码失败"),
    ;

    private final int code;
    private final String message;

    AuthErrorEnum(int code, String message) {
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
