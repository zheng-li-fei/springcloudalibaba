package com.zlf.serverauth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.zlf.api.commonapiauth.vo.AuthReqVO;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.utils.ResEx;
import com.zlf.serverauth.config.sentinel.AuthBlockHandler;
import com.zlf.serverauth.config.sentinel.AuthFallbackClass;
import com.zlf.serverauth.dao.AuthDao;
import com.zlf.serverauth.dao.entity.AuthDO;
import com.zlf.serverauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:35
 **/
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthDao authDao;

    @SentinelResource(value = "authRegister",
            blockHandler = "authRegisterBlockHandler", blockHandlerClass = AuthBlockHandler.class,
            fallback = "authRegisterFallback", fallbackClass = AuthFallbackClass.class, exceptionsToIgnore = BizException.class)
    @Override
    public Boolean authRegister(AuthReqVO authReqVO) {
        AuthDO authDO = BeanUtil.copyProperties(authReqVO, AuthDO.class);
        return authDao.insert(authDO) > 0;
    }

    @Override
    public Boolean authLogin(AuthReqVO authReqVO) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean authLoginOut(AuthReqVO authReqVO) {
        return Boolean.TRUE;
    }
}
