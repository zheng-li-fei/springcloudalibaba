package com.example.common.response;

import com.example.common.exception.CommonCodeEnumEx;
import com.example.common.exception.IErrorInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * replace of
 */
@ApiModel(value = "返回说明")
public class ResEx<T> {
    /**
     * 响应代码
     */
    @ApiModelProperty(value = "错误码：10000-成功，其他失败", required = true)
    private int code;

    /**
     * 全系统统一状态码提示字段
     */
    @ApiModelProperty(value = "描述信息", required = true)
    private String msg;

    /**
     * 响应结果
     */
    @ApiModelProperty(value = "返回结果对象", required = true)
    private T data;


    public ResEx() {
    }

    public ResEx(IErrorInfo errorInfo) {
        code = errorInfo.getCode();
        msg = errorInfo.getMessage();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResEx success() {
        return success(CommonCodeEnumEx.SUCCESSEx.getMessage());
    }


    /**
     * 成功
     *
     * @param message 自定义成功返回的提示文字
     * @return
     */
    public static ResEx success(String message) {
        return success(null, message);
    }


    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static ResEx success(Object data) {
        ResEx res = new ResEx();
        res.setCode(CommonCodeEnumEx.SUCCESSEx.getCode());
        res.setMsg(CommonCodeEnumEx.SUCCESSEx.getMessage());
        res.setData(data);
        return res;
    }

    /**
     * 成功
     *
     * @param data
     * @param message 自定义成功返回的提示文字
     * @return
     */
    public static ResEx success(Object data, String message) {
        ResEx res = new ResEx();
        res.setCode(CommonCodeEnumEx.SUCCESSEx.getCode());
        res.setMsg(message);
        res.setData(data);
        return res;
    }


    /**
     * 失败
     */
    public static ResEx error(IErrorInfo errorInfo) {
        ResEx res = new ResEx();
        res.setCode(errorInfo.getCode());
        res.setMsg(errorInfo.getMessage());
        res.setData(null);
        return res;
    }


    /**
     * 失败
     */
    public static ResEx error(int code, String message) {
        ResEx res = new ResEx();
        res.setCode(code);
        res.setMsg(message);
        res.setData(null);
        return res;
    }


    /**
     * 处理带返回信息的错误
     *
     * @param code    错误码
     * @param message 错误信息
     * @param data    返回数据
     * @return
     */
    public static ResEx error(int code, String message, Object data) {
        ResEx res = new ResEx();
        res.setCode(code);
        res.setMsg(message);
        res.setData(data);
        return res;
    }


    public boolean isSuccess() {
        return this.code == 10000;
    }


}
