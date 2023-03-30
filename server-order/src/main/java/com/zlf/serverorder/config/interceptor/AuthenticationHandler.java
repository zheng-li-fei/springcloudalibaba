package com.zlf.serverorder.config.interceptor;

import cn.hutool.jwt.JWTUtil;
import com.zlf.commonbase.annotation.ignoretoken.IgnoreTokenAccessAnnotation;
import com.zlf.commonbase.constant.CommonConstants;
import com.zlf.commonbase.content.UserContext;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.model.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

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
        //1.判断是否需要跳过拦截
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取方法上的注解
            IgnoreTokenAccessAnnotation ignoreTokenAccessAnnotation = handlerMethod.getMethod().getAnnotation(IgnoreTokenAccessAnnotation.class);
            //如果标记了注解,跳过拦截
            if (ignoreTokenAccessAnnotation != null) {
                return 1;
            }
        }

        //2.校验权限,验证token有效性
        String token = servletRequest.getHeader(CommonConstants.AUTHORIZATION);
        //2.1 token不为空且有效
        if (StringUtils.isBlank(token) || !JWTUtil.verify(token, CommonConstants.HMAC_KEY.getBytes())) {
            log.error("无效的token访问 {}", token);
            throw new BizException("无效的token访问");
        }
        //转为用户对象
        AuthUser authUser = JWTUtil.parseToken(token).getPayloads().toBean(AuthUser.class);
        UserContext.setLoginUser(authUser);
        return 1;
    }

}
