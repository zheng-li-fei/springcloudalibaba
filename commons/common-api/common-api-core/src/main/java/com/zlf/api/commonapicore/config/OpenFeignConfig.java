package com.zlf.api.commonapicore.config;

import cn.hutool.core.util.StrUtil;
import com.zlf.commonbase.constant.CommonConstants;
import com.zlf.commonbase.utils.MDCTraceUtils;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
        return Logger.Level.BASIC;
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

    /**
     * 请求头复制
     *
     * @return
     */
    @Bean
    @ConditionalOnClass(value = {RequestInterceptor.class})
    public RequestInterceptor headerInterceptor() {
        return template -> {
            //传递日志traceId
            String traceId = MDCTraceUtils.getTraceId();
            if (StrUtil.isNotEmpty(traceId)) {
                template.header(MDCTraceUtils.TRACE_ID_HEADER, traceId);
                template.header(MDCTraceUtils.SPAN_ID_HEADER, MDCTraceUtils.getNextSpanId());
            }

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                HttpServletRequest request = attributes.getRequest();
                //传递token，无网络隔离时需要传递
                String token = extractHeaderToken(request);
                if (StringUtils.isNotBlank(token)) {
                    template.header(CommonConstants.AUTHORIZATION, token);
                }
            }
        };
    }

    /**
     * 解析head中的token(authorization)
     *
     * @param request
     */
    private String extractHeaderToken(HttpServletRequest request) {
        //authorization
        return request.getHeader(CommonConstants.AUTHORIZATION);
    }

}