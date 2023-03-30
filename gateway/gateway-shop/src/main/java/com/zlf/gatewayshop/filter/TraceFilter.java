package com.zlf.gatewayshop.filter;

import com.zlf.commonbase.utils.MDCTraceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器,生成日志链路追踪id，并传入header中
 * 执行顺序:
 * 默认过滤器 → 当前路由过滤器 → 全局过滤器。
 */
@Slf4j
@Component
public class TraceFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //链路追踪id
        MDCTraceUtils.addTrace();
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(h -> {
            h.add(MDCTraceUtils.TRACE_ID_HEADER, MDCTraceUtils.getTraceId());
            h.add(MDCTraceUtils.SPAN_ID_HEADER, MDCTraceUtils.getNextSpanId());
        }).build();
        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        log.info("网关访问");
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
