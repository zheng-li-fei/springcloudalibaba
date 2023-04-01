package com.zlf.api.commonapiauth.feign;


import com.zlf.api.commonapiauth.feign.fallback.AuthServiceFeignFallbackFactory;
import com.zlf.api.commonapiauth.vo.req.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthLogoutReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthRegisterReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthUserListReqVO;
import com.zlf.api.commonapiauth.vo.res.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.res.AuthUserListResVO;
import com.zlf.api.commonapicore.config.OpenFeignConfig;
import com.zlf.commonbase.annotation.ignoretoken.IgnoreTokenAccessAnnotation;
import com.zlf.commonbase.annotation.ignoretoken.IgnoreTokenTypeEnum;
import com.zlf.commonbase.constant.ServiceNameConstants;
import com.zlf.commonbase.model.base.PageQueryResponse;
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
     * 获取用户列表
     *
     * @param reqVO 请求参数
     * @return ResEx
     */
    @RequestMapping(value = "/auth/getUserListByPage", method = RequestMethod.POST)
    ResEx<PageQueryResponse<AuthUserListResVO>> getUserListByPage(@RequestBody AuthUserListReqVO reqVO);

    /**
     * 退出
     *
     * @param loginOutReqVO 请求参数
     * @return ResEx
     */
    @RequestMapping(value = "/auth/logout", method = RequestMethod.POST)
    ResEx<Boolean> authLoginOut(@RequestBody AuthLogoutReqVO loginOutReqVO);

}
