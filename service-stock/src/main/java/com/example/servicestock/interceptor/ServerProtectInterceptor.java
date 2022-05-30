package com.example.servicestock.interceptor;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.example.common.response.ResEx;
import com.example.servicestock.config.CloudSecurityProperties;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ServerProtectInterceptor implements HandlerInterceptor {

    private CloudSecurityProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        log.info("全局事务xid:{}", RootContext.getXID());

        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }

        String token = request.getHeader("CloudConstant.GATEWAY_TOKEN_HEADER");

        String gatewayToken = new String(Base64Utils.encode("CloudConstant.GATEWAY_TOKEN_VALUE".getBytes()));

        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            log.error("请通过网关访问资源");
            response.getWriter().write(JSON.toJSONString(ResEx.error(HttpServletResponse.SC_FORBIDDEN,"Please access the resource through the gateway")));
            return false;
        }
    }

    public void setProperties(CloudSecurityProperties properties) {
        this.properties = properties;
    }
}