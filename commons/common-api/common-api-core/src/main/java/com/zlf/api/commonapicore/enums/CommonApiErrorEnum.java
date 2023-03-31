package com.zlf.api.commonapicore.enums;


import com.zlf.commonbase.constant.IErrorInfo;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/11/10 15:23
 **/
public enum  CommonApiErrorEnum implements IErrorInfo {

    /**
     * 通用服务 Api 错误枚举
     */
    SERVICE_LIMIT_ACCESS(500, "访问频繁，请稍候再试"),
    SERVICE_NOT_EXIST(500, "服务暂时不可用，请稍候再试"),

    ;

    private final int code;
    private final String message;

    CommonApiErrorEnum(int code, String message) {
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
