package com.zlf.serverauth.service.impl;

import com.zlf.api.commonapiauth.vo.req.AuthLogoutReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.res.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.req.AuthRegisterReqVO;
import com.zlf.serverauth.dao.AuthDao;
import com.zlf.serverauth.enums.LoginTypeEnum;
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

    /**
     * 校验登录参数
     * @param loginTypeEnum 登录类型
     * @param loginReqVO    请求参数
     */
    @Override
    protected void validLoginParam(LoginTypeEnum loginTypeEnum, AuthLoginReqVO loginReqVO) {

    }

    /**
     * 账号+密码登录
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getAccountPasswordLoginInfo(AuthLoginReqVO loginReqVO) {
        return null;
    }

    /**
     * 手机号+密码登录
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getPhonePasswordLoginInfo(AuthLoginReqVO loginReqVO) {
        return null;
    }

    /**
     * 手机号+验证码
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getPhoneCodeLoginInfo(AuthLoginReqVO loginReqVO) {
        return null;
    }

    /**
     * 第三方登录
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getThirdLoginInfo(AuthLoginReqVO loginReqVO) {
        return null;
    }

    @Override
    public AuthLoginResVO authLogin(AuthLoginReqVO loginReqVO) {
        return super.authLogin(loginReqVO);
    }

    @Override
    public Boolean authLoginOut(AuthLogoutReqVO loginOutReqVO) {
        return Boolean.TRUE;
    }

}
