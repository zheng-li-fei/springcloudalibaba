

package com.zlf.serverauth.config.interceptor;


import com.zlf.commonbase.constant.CommonConstants;
import com.zlf.commonbase.utils.GatewayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Desc: 鉴权
 * @author: yangxingyao
 * @date: 2021/1/25 14:32
 * @warning：本内容仅限于浙江壹企通科技发展有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    AuthenticationHandler authenticationHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //校验网关标识
        GatewayUtil.checkGatewayTimestamp(request.getHeader(CommonConstants.GATEWAY_CHECK_TIMESTAMP));
        authenticationHandler.parseHttpToken(request, handler);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {
    }
}
