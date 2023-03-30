package com.zlf.commonbase.enums;


import com.zlf.commonbase.constant.IErrorInfo;

/**
 * 扩展状态码
 */
public enum CommonCodeEnumEx implements IErrorInfo {

    /**
     * 通用错误码
     */
    SUCCESS(10000, "成功"),
    SUCCESSEx(10000, "Success"),

    GATEWAY_FLAG_LOST(91209100, "未授权的访问请求"),

    GATEWAY_TIMEOUT(91209101, "请求访问超时"),
    ;

    private final int code;
    private final String message;

    CommonCodeEnumEx(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + code + "]" + message;
    }
}
