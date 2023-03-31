package com.zlf.serverauth.config;


import com.zlf.commonbase.exception.BizDataException;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.utils.ResEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @describle: 全局异常处理
 * @author: zhenglifei
 * @create: 2020/12/18
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionConfig {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResEx exceptionHandler(Exception e) {
        log.error("error ==>",e);
        return ResEx.error(500,"商城失联了，请稍后再试");
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResEx exceptionHandler(BizException e) {
        log.error("biz error ==>",e);
        return ResEx.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(BizDataException.class)
    @ResponseBody
    public ResEx exceptionHandler(BizDataException e) {
        log.error("biz error 带返回数据的异常 ==> e {}", e.toString());
        return ResEx.error(e.getCode(), e.getMessage(), e.getData());
    }

    /**
     * 入参参数校验
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResEx argsNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error("参数校验不通过 ==>", e);
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResEx.error(500, errorMessage);
    }

    /**
     * 入参参数校验
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResEx httpRequestMethodNotSupportedExceptionHandle(HttpRequestMethodNotSupportedException e) {
        return ResEx.error(500, "不支持的方法类型");
    }

}
