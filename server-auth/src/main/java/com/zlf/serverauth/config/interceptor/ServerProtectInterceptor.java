package com.zlf.serverauth.config.interceptor;

import lombok.extern.slf4j.Slf4j;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 如果是OPTIONS则结束请求,返回跨域信息
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setHeader("Access-Control-Max-Age", "1800");
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        return true;
    }
}