package com.zlf.api.commonapicore.config;

/**
 * @author ：zhenglifei
 * @description ：ChannelFilter.class
 * @date ：Created in 2022/4/26 14:05
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * request.getInputStream() 只能够读取一次
 * 这里重写HttpServletRequestWrapper把request保存下来，然后通过过滤器把保存下来的request再填充进去，这样就可以多次读取request了。
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "channelFilter")
public class ChannelFilter implements Filter {


    private static final String APPLICATION_JSON = "application/json";
    private static final String POST = "POST";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            String contentType = servletRequest.getContentType();
            if (contentType != null && contentType.contains(APPLICATION_JSON)) {
                HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
                String method = httpServletRequest.getMethod();
                if(StringUtils.equalsIgnoreCase(method, POST)){
                    requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
                }
            }
        }

        if (requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

}

