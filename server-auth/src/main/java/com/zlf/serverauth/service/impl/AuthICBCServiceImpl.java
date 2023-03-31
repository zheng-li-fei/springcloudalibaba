package com.zlf.serverauth.service.impl;

import com.zlf.api.commonapiauth.vo.AuthLoginOutReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.AuthRegisterReqVO;
import com.zlf.serverauth.dao.AuthDao;
import com.zlf.serverauth.enums.PlatformTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 工行实现类
 * @author: zhenglifei
 * @create: 2022/4/19 10:35
 **/
@Slf4j
@Service
public class AuthICBCServiceImpl extends AbstractAuthService {

    @Autowired
    AuthDao authDao;

    @Override
    public PlatformTypeEnum getPlatformType() {
        return PlatformTypeEnum.ICBC;
    }

    /**
     * 手机号 + 密码
     *
     * @param registerReqVO 请求参数
     * @return Boolean
     */
    @Override
    public Boolean authRegister(AuthRegisterReqVO registerReqVO) {
        return Boolean.TRUE;
    }

    @Override
    public AuthLoginResVO authLogin(AuthLoginReqVO loginReqVO) {
        return AuthLoginResVO.builder().build();
    }

    @Override
    public Boolean authLoginOut(AuthLoginOutReqVO loginOutReqVO) {
        return Boolean.TRUE;
    }

}
