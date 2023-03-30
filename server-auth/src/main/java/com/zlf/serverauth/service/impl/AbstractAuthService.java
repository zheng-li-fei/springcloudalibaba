package com.zlf.serverauth.service.impl;

import com.zlf.api.commonapiauth.vo.AuthLoginOutReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.AuthRegisterReqVO;
import com.zlf.commonbase.exception.BizException;
import com.zlf.serverauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 认证服务抽象实现类
 * @author: zhenglifei
 * @date: 2023/3/29 15:35
 */
@Slf4j
@Component
public abstract class AbstractAuthService implements AuthService {

    @Override
    public Boolean authRegister(AuthRegisterReqVO registerReqVO) {
        throw new BizException("暂未支持");
    }

    @Override
    public AuthLoginResVO authLogin(AuthLoginReqVO loginReqVO) {
        throw new BizException("暂未支持");
    }

    @Override
    public Boolean authLoginOut(AuthLoginOutReqVO loginOutReqVO) {
        throw new BizException("暂未支持");
    }
}
