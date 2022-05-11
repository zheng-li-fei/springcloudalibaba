package com.example.common.exception;

public enum CommonCodeEnumEx implements IErrorInfo {
    SUCCESS(10000, "成功"),
    SUCCESSEx(10000, "Success");

    private final int code;
    private final String message;

    private CommonCodeEnumEx(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "[" + this.code + "]" + this.message;
    }
}