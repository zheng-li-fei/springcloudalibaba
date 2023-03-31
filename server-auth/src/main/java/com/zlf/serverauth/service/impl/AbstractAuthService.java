package com.zlf.serverauth.service.impl;

import com.zlf.api.commonapiauth.vo.AuthLoginOutReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.AuthRegisterReqVO;
import com.zlf.commonbase.exception.BizException;
import com.zlf.serverauth.enums.LoginTypeEnum;
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

    /**
     * 校验登录并返回用户信息
     *
     * @param loginTypeEnum 登录类型
     * @param loginReqVO    请求参数
     */
    protected abstract void validLoginParam(LoginTypeEnum loginTypeEnum, AuthLoginReqVO loginReqVO);

    /**
     * 1.账号密码登录并返回登录信息
     *
     * @param loginReqVO 请求参数
     * @return AuthLoginResVO
     */
    protected abstract AuthLoginResVO getAccountPasswordLoginInfo(AuthLoginReqVO loginReqVO);
    /**
     * 2.手机号密码登录并返回登录信息
     *
     * @param loginReqVO 请求参数
     * @return AuthLoginResVO
     */
    protected abstract AuthLoginResVO getPhonePasswordLoginInfo(AuthLoginReqVO loginReqVO);
    /**
     * 3.手机号验证码登录并返回登录信息
     *
     * @param loginReqVO 请求参数
     * @return AuthLoginResVO
     */
    protected abstract AuthLoginResVO getPhoneCodeLoginInfo(AuthLoginReqVO loginReqVO);
    /**
     * 4.第三方登录并返回登录信息
     *
     * @param loginReqVO 请求参数
     * @return AuthLoginResVO
     */
    protected abstract AuthLoginResVO getThirdLoginInfo(AuthLoginReqVO loginReqVO);

    @Override
    public AuthLoginResVO authLogin(AuthLoginReqVO loginReqVO) {
        String loginType = loginReqVO.getLoginType();
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.getLoginTypeEnumByType(loginType);
        log.info("开始认证，认证方法为【{}】", loginTypeEnum.getDesc());

        //根据登录类型校验登录参数
        validLoginParam(loginTypeEnum, loginReqVO);

        AuthLoginResVO loginResponseInfo;
        if (loginTypeEnum.isAccountPasswordLogin()) {
            //账号+密码
            loginResponseInfo = getAccountPasswordLoginInfo(loginReqVO);
        } else if (loginTypeEnum.isPhonePasswordLogin()) {
            //手机号+密码
            loginResponseInfo = getPhonePasswordLoginInfo(loginReqVO);
        } else if (loginTypeEnum.isPhoneCodeLogin()) {
            //手机号+验证码
            loginResponseInfo = getPhoneCodeLoginInfo(loginReqVO);
        } else if (loginTypeEnum.isThirdLogin()) {
            //第三方登录
            loginResponseInfo = getThirdLoginInfo(loginReqVO);
        } else {
            throw new BizException("暂未支持");
        }

        return AuthLoginResVO.builder()
                .platformType(loginResponseInfo.getPlatformType())
                .userId(loginResponseInfo.getUserId())
                .token(loginResponseInfo.getToken())
                .build();
    }

    @Override
    public Boolean authLoginOut(AuthLoginOutReqVO loginOutReqVO) {
        throw new BizException("暂未支持");
    }
}
