package com.example.gateway.filter;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Order(4)
public class GatewayRequestFilter implements GlobalFilter {
 
    @Override 
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        byte[] token = Base64Utils.encode(("CloudConstant.GATEWAY_TOKEN_VALUE").getBytes());
        String[] headerValues = {new String(token)}; 
        ServerHttpRequest build = exchange.getRequest()
                .mutate() 
                .header("CloudConstant.GATEWAY_TOKEN_HEADER", headerValues)
                .build();
        log.info("请求网关,设置 gateway_token_value {}",headerValues);

        log.info("全局事务xid:{}", RootContext.getXID());

        ServerWebExchange newExchange = exchange.mutate().request(build).build(); 
        return chain.filter(newExchange);
    } 
 
}