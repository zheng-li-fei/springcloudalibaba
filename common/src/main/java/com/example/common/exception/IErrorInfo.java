package com.example.common.exception;

public interface IErrorInfo {
    /**
     * 错误码
     */
    public int getCode();

    /**
     * 错误描述
     */
    public String getMessage();
}
