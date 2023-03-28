package com.zlf.serverauth.service;


import com.zlf.api.commonapiauth.vo.AuthReqVO;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:34
 **/
public interface AuthService {
    Boolean authRegister(AuthReqVO authReqVO);

    Boolean authLogin(AuthReqVO authReqVO);

    Boolean authLoginOut(AuthReqVO authReqVO);

}
