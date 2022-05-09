package com.example.common.exception;

/**
 * 自定义异常
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;
    private int code = 500;

    public BizException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BizException(IErrorInfo iErrorInfo) {
        super(iErrorInfo.getMessage());
        this.code = iErrorInfo.getCode();
        this.message = iErrorInfo.getMessage();
    }

    public BizException(String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public BizException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
