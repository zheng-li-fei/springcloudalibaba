package com.zlf.api.commonapiauth.feign;


import com.zlf.api.commonapiauth.feign.fallback.AuthServiceFeignFallbackFactory;
import com.zlf.api.commonapiauth.vo.AuthLoginOutReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.AuthRegisterReqVO;
import com.zlf.api.commonapicore.config.OpenFeignConfig;
import com.zlf.commonbase.annotation.ignoretoken.IgnoreTokenAccessAnnotation;
import com.zlf.commonbase.annotation.ignoretoken.IgnoreTokenTypeEnum;
import com.zlf.commonbase.constant.ServiceNameConstants;
import com.zlf.commonbase.utils.ResEx;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 库存服务
 * @author: zhenglifei
 * @create: 2022/11/9 17:06
 **/
@FeignClient(value = ServiceNameConstants.SERVER_AUTH, fallbackFactory = AuthServiceFeignFallbackFactory.class, configuration = OpenFeignConfig.class)
public interface AuthFeignClient {

    /**
     * 注册
     *
     * @param registerReqVO 请求参数
     * @return ResEx
     */
    @IgnoreTokenAccessAnnotation(type = IgnoreTokenTypeEnum.SHOP_REGISTER,remark = "商城用户注册")
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    ResEx<Boolean> authRegister(@RequestBody AuthRegisterReqVO registerReqVO);

    /**
     * 登录
     *
     * @param loginReqVO 请求参数
     * @return ResEx
     */
    @IgnoreTokenAccessAnnotation(type = IgnoreTokenTypeEnum.SHOP_LOGIN,remark = "商城用户登录")
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    ResEx<AuthLoginResVO> authLogin(HttpServletRequest request, @RequestBody AuthLoginReqVO loginReqVO);

    /**
     * 退出
     *
     * @param loginOutReqVO 请求参数
     * @return ResEx
     */
    @RequestMapping(value = "/auth/loginOut", method = RequestMethod.POST)
    ResEx<Boolean> authLoginOut(@RequestBody AuthLoginOutReqVO loginOutReqVO);

}
