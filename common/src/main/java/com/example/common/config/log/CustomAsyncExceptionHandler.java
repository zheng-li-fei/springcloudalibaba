package com.example.common.config.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 异步消息异常处理
 */
@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        log.error("=======异步事件消息异常日志开始=======");
        log.error("Method {},Exception message {}", method, throwable);
        for (Object param : obj) {
            log.error("Parameter value - " + param);
        }
        log.error("=======异步事件消息异常日志结束=======");

    }

}