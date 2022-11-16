package com.zlf.commonbase.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import com.zlf.commonbase.constant.IErrorInfo;
import com.zlf.commonbase.enums.CommonCodeEnumEx;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


/**
 *
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

    /**
     * 处理带堆栈信息的错误
     *
     * @param code      错误码
     * @param message   错误信息
     * @param data      返回数据
     * @param exception 异常错误对象
     * @return
     */
    public static ResEx error(int code, String message, Object data, Throwable exception) {
        ResEx res = new ResEx();
        res.setCode(code);
        res.setMsg(message);
        res.setData(data);
        try {
            //获取错误的堆栈信息
            String errMsg = getStackTrace(exception);
            //限制 errMsg 长度
            if (StringUtils.isNotBlank(errMsg) && errMsg.length() >= 5000) {
                errMsg = errMsg.substring(0, 5000);
            }
            errMsg = zipString(errMsg);
            //res.setErrMsg(errMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param errorInfo
     * @param message
     * @return
     */
    public static ResEx error(IErrorInfo errorInfo, String message) {
        ResEx res = new ResEx();
        res.setCode(errorInfo.getCode());
        res.setMsg(message);
        res.setData(null);
        return res;
    }

    /*********************************************打印错误堆栈*************************************************/
    /**
     * 压缩
     */
    public static String zipString(String unzipString) {
        /**
         *     https://www.yiibai.com/javazip/javazip_deflater.html#article-start
         *     0 ~ 9 压缩等级 低到高
         *     public static final int BEST_COMPRESSION = 9;            最佳压缩的压缩级别。
         *     public static final int BEST_SPEED = 1;                  压缩级别最快的压缩。
         *     public static final int DEFAULT_COMPRESSION = -1;        默认压缩级别。
         *     public static final int DEFAULT_STRATEGY = 0;            默认压缩策略。
         *     public static final int DEFLATED = 8;                    压缩算法的压缩方法(目前唯一支持的压缩方法)。
         *     public static final int FILTERED = 1;                    压缩策略最适用于大部分数值较小且数据分布随机分布的数据。
         *     public static final int FULL_FLUSH = 3;                  压缩刷新模式，用于清除所有待处理的输出并重置拆卸器。
         *     public static final int HUFFMAN_ONLY = 2;                仅用于霍夫曼编码的压缩策略。
         *     public static final int NO_COMPRESSION = 0;              不压缩的压缩级别。
         *     public static final int NO_FLUSH = 0;                    用于实现最佳压缩结果的压缩刷新模式。
         *     public static final int SYNC_FLUSH = 2;                  用于清除所有未决输出的压缩刷新模式; 可能会降低某些压缩算法的压缩率。
         */
        if (StringUtils.isBlank(unzipString)) {
            unzipString = "错误信息为空";
        }
        //使用指定的压缩级别创建一个新的压缩器。
        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        //设置压缩输入数据。
        deflater.setInput(unzipString.getBytes());
        //当被调用时，表示压缩应该以输入缓冲区的当前内容结束。
        deflater.finish();

        final byte[] bytes = new byte[256];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256);

        while (!deflater.finished()) {
            //压缩输入数据并用压缩数据填充指定的缓冲区。
            int length = deflater.deflate(bytes);
            outputStream.write(bytes, 0, length);
        }
        //关闭压缩器并丢弃任何未处理的输入。
        deflater.end();
        return Base64.encode(outputStream.toByteArray());
    }

    /**
     * 解压缩
     */
    public static String unzipString(String zipString) {
        byte[] decode = Base64.decode(zipString);
        Inflater inflater = new Inflater();
        //设置解压缩的输入数据。
        inflater.setInput(decode);
        final byte[] bytes = new byte[256];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256);
        try {
            //finished() 如果已到达压缩数据流的末尾，则返回true。
            while (!inflater.finished()) {
                //将字节解压缩到指定的缓冲区中。
                int length = inflater.inflate(bytes);
                outputStream.write(bytes, 0, length);
            }
        } catch (DataFormatException e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭解压缩器并丢弃任何未处理的输入。
            inflater.end();
        }

        return outputStream.toString();
    }

    /**
     * 获取错误的堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        if (throwable == null) {
            return "错误对象 e 为空 , 没有错误堆栈";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        try {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            IoUtil.close(printWriter);
            IoUtil.close(stringWriter);
        }

    }

}
