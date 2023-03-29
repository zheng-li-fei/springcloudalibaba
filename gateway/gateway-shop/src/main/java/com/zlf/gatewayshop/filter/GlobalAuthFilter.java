package com.zlf.gatewayshop.filter;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.zlf.commonbase.constant.CommonConstants;
import com.zlf.commonbase.utils.MDCTraceUtils;
import com.zlf.commonredis.constants.RedisConstant;
import com.zlf.gatewayshop.config.GlobalEnvironmentConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局过滤器,校验权限
 */
@Slf4j
@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //当前请求路径
        String reqPath = request.getURI().getPath();

        //1.需要跳过鉴权的接口集合
        List<String> ignoreInterfaceList = new ArrayList<>();
        ignoreInterfaceList.add("/auth/register");
        ignoreInterfaceList.add("/auth/login");
        boolean ignore = ignoreInterfaceList.stream().anyMatch(reqPath::contains);
        if(!ignore){
            //2.校验权限,验证token有效性
            String token = exchange.getRequest().getHeaders().getFirst(CommonConstants.AUTHORIZATION);
            //2.1 token不为空且有效
            if(StringUtils.isBlank(token) || !JWTUtil.verify(token, CommonConstants.HMAC_KEY.getBytes())){
                log.error("无效的token访问 {}", token);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            String userId = convertToken2UserId(token);
            //2.2 redis中存在token(jwt的token不会自动失效)
            redisTemplate.hasKey(String.format(RedisConstant.SERVER_AUTH_SHOP_LOGIN_USERID_STR,userId));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * token 解析为 userId
     * @param token
     * @return
     */
    public String convertToken2UserId(String token){
        return "";
    }

}
