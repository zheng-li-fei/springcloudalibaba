package com.example.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.example.common.response.ResEx;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description: 全局过滤器
 * @author: zhenglifei
 * @create: 2022/4/25 14:23
 **/
@Slf4j
@Order(value = 3)
@Component
public class GlobalAuthGatewayFilterFactory implements GlobalFilter {

    private static final String HEADER_TOKEN_NAME = "authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();

        String token = headers.getFirst(HEADER_TOKEN_NAME);
        //1.判断是否传了token
        if (StringUtils.isBlank(token)) {
            log.error("全局权限过滤器,请求资源地址:{},未登录", request.getURI());
            //响应内容
            String message = JSON.toJSONString(ResEx.error(HttpStatus.UNAUTHORIZED.value(), "authorization authentication failed"));
            DataBuffer buffer = response.bufferFactory().wrap(message.getBytes());
            return response.writeWith(Mono.just(buffer));
        } else {
            log.info("请求网关,判断token是否存在: true");
        }

        //2.判断token是否合法

        //继续执行
        return chain.filter(exchange);
    }
}
