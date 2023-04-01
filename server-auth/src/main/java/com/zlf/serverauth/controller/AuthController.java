package com.zlf.serverauth.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.zlf.api.commonapiauth.feign.AuthFeignClient;
import com.zlf.api.commonapiauth.vo.req.AuthLogoutReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthRegisterReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthUserListReqVO;
import com.zlf.api.commonapiauth.vo.res.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.res.AuthUserListResVO;
import com.zlf.commonbase.content.UserContext;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.model.base.PageQueryResponse;
import com.zlf.commonbase.utils.ResEx;
import com.zlf.serverauth.context.AuthContextUtil;
import com.zlf.serverauth.enums.LoginTypeEnum;
import com.zlf.serverauth.enums.PlatformTypeEnum;
import com.zlf.serverauth.enums.error.AuthErrorEnum;
import com.zlf.serverauth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:27
 **/
@Slf4j
@RestController
public class AuthController implements AuthFeignClient {

    /**
     * 注册
     *
     * @param registerReqVO 请求参数
     * @return ResEx
     */
    @Override
    public ResEx<Boolean> authRegister(AuthRegisterReqVO registerReqVO) {
        //请求来源平台类型
        Integer platformType = registerReqVO.getPlatformType();
        AuthService authServer = getAuthServer(platformType);
        return ResEx.success(authServer.authRegister(registerReqVO));
    }

    /**
     * 登录
     *
     * @param loginReqVO 请求参数
     * @return ResEx
     */
    @Override
    public ResEx<AuthLoginResVO> authLogin(HttpServletRequest request, AuthLoginReqVO loginReqVO) {
        //请求来源平台类型
        Integer platformType = loginReqVO.getPlatformType();
        //登录类型
        String loginType = loginReqVO.getLoginType();
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.getLoginTypeEnumByType(loginType);
        if (loginTypeEnum == null) {
            log.error("非法登录,登录类型非法 loginType {},platformType {}", loginType, platformType);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_ILLEGAL_LOGIN_TYPE);
        }
        //根据类型获取登录上下文对象
        AuthService authService = getAuthServer(platformType);
        //封装客户端ip
        loginReqVO.setLastLoginIp(ServletUtil.getClientIP(request));
        return ResEx.success(authService.authLogin(loginReqVO));
    }

    @Override
    public ResEx<PageQueryResponse<AuthUserListResVO>> getUserListByPage(AuthUserListReqVO reqVO) {
        Integer platformType = UserContext.getPlatformType();
        //根据类型获取登录上下文对象
        AuthService authService = getAuthServer(platformType);
        reqVO.setPlatformType(platformType);
        return ResEx.success(authService.getUserListByPage(reqVO));
    }

    /**
     * 退出
     *
     * @param loginOutReqVO 请求参数
     * @return ResEx
     */
    @Override
    public ResEx<Boolean> authLoginOut(AuthLogoutReqVO loginOutReqVO) {
        //请求来源平台类型
        Integer platformType = loginOutReqVO.getPlatformType();
        AuthService authServer = getAuthServer(platformType);
        loginOutReqVO.setUserId(UserContext.getLoginUser().getUserId());
        return ResEx.success(authServer.authLoginOut(loginOutReqVO));
    }



    /**
     * 获取服务类
     *
     * @param platformType 请求来源平台类型
     * @return
     */
    private AuthService getAuthServer(Integer platformType) {
        //校验类型
        PlatformTypeEnum platformTypeEnum = PlatformTypeEnum.getPlatformTypeEnumByType(platformType);
        if (platformTypeEnum == null) {
            log.error("非法登录,请求来源类型非法 platformType {}", platformType);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_ILLEGAL_PLATFORM_TYPE);
        }
        return AuthContextUtil.getAuthServiceByLoginType(platformTypeEnum);
    }
}
