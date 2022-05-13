package com.example.servicestock.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OpenFeignConfig {
    /**
     * NONE：默认的，不显示任何日志；
     * BASIC：仅记录请求方法、URL、响应状态码及执行时间；
     * HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息；
     * FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据；
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    /**
     * 超时时间配置
     *
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true);
    }
}