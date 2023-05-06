package com.zlf.gatewayshop.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description: 自定义配置文件
 * @author: zhenglifei
 * @create: 2022/10/9 15:21
 **/
@Data
@RefreshScope
@Configuration
public class GlobalEnvironmentConfig {

    /**
     * 是否开启链路追踪
     */
    @Value("${log.trace.enable:true}")
    public boolean logTraceEnable;

    /**
     * 无需鉴权地址集合
     */
    @Value("${server.ignore.token.urls}")
    public List<String> ignoreTokenUrls;
}
