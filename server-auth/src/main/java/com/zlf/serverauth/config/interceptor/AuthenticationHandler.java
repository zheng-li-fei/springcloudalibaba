package com.zlf.serverauth.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc:
 * @author: yangxingyao
 * @date: 2021/1/25 15:13
 * @warning：本内容仅限于浙江壹企通科技发展有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
@Slf4j
public class AuthenticationHandler {

    /**
     * 通过http请求处理access token
     */
    public int parseHttpToken(HttpServletRequest servletRequest, Object handler) {
        return 1;
    }

}
