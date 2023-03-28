package com.zlf.serverauth.controller;

import com.zlf.api.commonapiauth.feign.AuthFeignClient;
import com.zlf.api.commonapiauth.vo.AuthReqVO;
import com.zlf.commonbase.utils.ResEx;
import com.zlf.serverauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:27
 **/
@Slf4j
@RestController
public class AuthController implements AuthFeignClient {

    @Autowired
    AuthService authService;

    @Override
    public ResEx<Boolean> authRegister(AuthReqVO authReqVO) {
        return ResEx.success(authService.authRegister(authReqVO));
    }

    @Override
    public ResEx<Boolean> authLogin(AuthReqVO authReqVO) {
        return ResEx.success(authService.authLogin(authReqVO));
    }

    @Override
    public ResEx<Boolean> authLoginOut(AuthReqVO authReqVO) {
        return ResEx.success(authService.authLoginOut(authReqVO));
    }
}
