package com.zlf.gatewayshop.filter;

import cn.hutool.jwt.JWTUtil;
import com.zlf.commonbase.constant.CommonConstants;
import com.zlf.commonbase.constant.redis.RedisKeyConstant;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.model.AuthUser;
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
    RedisTemplate<String, String> redisTemplate;

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
        if (!ignore) {
            //2.校验权限,验证token有效性
            String token = exchange.getRequest().getHeaders().getFirst(CommonConstants.AUTHORIZATION);
            //2.1 token不为空且有效
            if (StringUtils.isBlank(token) || !JWTUtil.verify(token, CommonConstants.HMAC_KEY.getBytes())) {
                log.error("无效的token访问 {}", token);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            //转为用户对象
            AuthUser authUser = JWTUtil.parseToken(token).getPayloads().toBean(AuthUser.class);
            //2.2 redis中存在token(jwt的token不会自动失效)
            String redisKey = String.format(RedisKeyConstant.SERVER_AUTH_SHOP_LOGIN_USERID_STR, authUser.getUserId());
            Boolean aBoolean = redisTemplate.hasKey(redisKey);
            if(aBoolean == null || !aBoolean){
                //token不存在或已失效
                log.error("用户登录过期，请重新登录");
                throw new BizException("用户登录过期，请重新登录");
            }
            //2.3 redis缓存中的token和当前token一致
            String tokenValue = redisTemplate.opsForValue().get(redisKey);
            if(!StringUtils.equals(token,tokenValue)){
                //当前token已失效
                log.error("用户登录过期，请重新登录");
                throw new BizException("用户登录过期，请重新登录");
            }
        }
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(h -> {
            //添加通过网关校验时间戳,5分钟内有效
            h.add(CommonConstants.GATEWAY_CHECK_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        }).build();
        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
