package com.example.common.exception;

public interface IErrorInfo {
    /**
     * 错误码
     */
    int getCode();

    /**
     * 错误描述
     */
    String getMessage();
}
