package com.zlf.serverauth.context;

import com.zlf.commonbase.exception.BizException;
import com.zlf.serverauth.enums.PlatformTypeEnum;
import com.zlf.serverauth.enums.error.AuthErrorEnum;
import com.zlf.serverauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 上下文工具类
 * @author: zhenglifei
 * @date: 2023/3/29 16:52
 */
@Slf4j
@Service
public class AuthContextUtil implements ApplicationContextAware {

    private static final Map<PlatformTypeEnum, AuthService> authServiceMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AuthService> authMap = applicationContext.getBeansOfType(AuthService.class);
        if (authMap.size() > 0) {
            authMap.forEach((s, authService) -> authServiceMap.put(authService.getPlatformType(), authService));
        }
    }

    public static AuthService getAuthServiceByLoginType(PlatformTypeEnum typeEnum) {
        if (typeEnum == null) {
            log.error("根据平台类型获取服务接口,请求参数错误");
            throw new BizException(AuthErrorEnum.SERVER_AUTH_TYPE_ERROR);
        }
        AuthService authService = authServiceMap.get(typeEnum);
        if (authService == null) {
            log.error("根据平台类型获取服务接口,枚举对应接口不存在 typeEnum {}", typeEnum);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_BEAN_NOT_FOUND);
        }
        return authService;
    }


}
