package com.zlf.serverapi.config.interceptor;

import com.zlf.baseproject.exception.BizException;
import com.zlf.serverapi.config.GlobalEnvironmentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ServerProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private GlobalEnvironmentConfig environmentConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (environmentConfig.isGatewayCloudOnlyFetchByGateway()) {
            //只能从网关访问
            log.error("请通过网关访问资源");
            throw new BizException(HttpServletResponse.SC_FORBIDDEN, "Please access the resource through the gateway");
        }
        // 如果是OPTIONS则结束请求,返回跨域信息
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setHeader("Access-Control-Max-Age", "86400");
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false; // 跳出拦截调用链
        }
        return true;
    }
}