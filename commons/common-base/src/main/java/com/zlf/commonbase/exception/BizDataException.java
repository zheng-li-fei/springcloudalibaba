package com.zlf.commonbase.exception;

import cn.hutool.json.JSONUtil;
import com.zlf.commonbase.constant.IErrorInfo;

/**
 * 自定义异常, 异常中带数据
 *
 * @author yangxingyao02@bianfeng.com
 */
public class BizDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code = 500;
    private String message;
    private Object data;

    public BizDataException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BizDataException(IErrorInfo iErrorInfo) {
        super(iErrorInfo.getMessage());
        this.code = iErrorInfo.getCode();
        this.message = iErrorInfo.getMessage();
    }

    public BizDataException(String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
    }

    public BizDataException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public BizDataException(int code, String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
        this.code = code;
    }

    public BizDataException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.message = msg;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BizDataException{" + "code=" + code + ", message='" + message + '\'' + ", data=" + JSONUtil.toJsonStr(data) + '}';
    }
}
