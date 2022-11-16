package com.zlf.commonbase.exception;


import com.zlf.commonbase.constant.IErrorInfo;

/**
 * 自定义异常
 *
 * @author yangxingyao02@bianfeng.com
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;
    private int code = 500;
    private boolean noLogPrint;

    public BizException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BizException(IErrorInfo iErrorInfo) {
        super(iErrorInfo.getMessage());
        this.message = iErrorInfo.getMessage();
        this.code = iErrorInfo.getCode();
    }

    public BizException(String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
    }

    public BizException(String msg, int code) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public BizException(int code, String msg) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public BizException(String msg, int code, Throwable e) {
        super(msg, e);
        this.message = msg;
        this.code = code;
    }

    public BizException(int code, String msg, boolean noLogPrint) {
        super(msg);
        this.message = msg;
        this.code = code;
        this.noLogPrint = noLogPrint;
    }

    public static BizException noLogPrintBizException(int code, String msg) {
        return new BizException(code, msg, true);
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

    public boolean getNoLogPrint() {
        return noLogPrint;
    }

    public void setNoLogPrint(Boolean noLogPrint) {
        this.noLogPrint = noLogPrint;
    }
}
