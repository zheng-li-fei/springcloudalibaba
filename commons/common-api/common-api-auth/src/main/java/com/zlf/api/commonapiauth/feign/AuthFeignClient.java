package com.zlf.api.commonapiauth.feign;


import com.zlf.api.commonapiauth.feign.fallback.AuthServiceFeignFallbackFactory;
import com.zlf.api.commonapiauth.vo.AuthReqVO;
import com.zlf.commonapicore.config.OpenFeignConfig;
import com.zlf.commonbase.constant.ServiceNameConstants;
import com.zlf.commonbase.utils.ResEx;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 库存服务
 * @author: zhenglifei
 * @create: 2022/11/9 17:06
 **/
@FeignClient(value = ServiceNameConstants.SERVER_STOCK, fallbackFactory = AuthServiceFeignFallbackFactory.class, configuration = OpenFeignConfig.class)
public interface AuthFeignClient {

    /**
     * 注册
     *
     * @param authReqVO
     */
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    ResEx<Boolean> authRegister(@RequestBody AuthReqVO authReqVO);

    /**
     * 登录
     *
     * @param authReqVO
     */
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    ResEx<Boolean> authLogin(@RequestBody AuthReqVO authReqVO);

    /**
     * 退出
     *
     * @param authReqVO
     */
    @RequestMapping(value = "/auth/loginOut", method = RequestMethod.POST)
    ResEx<Boolean> authLoginOut(@RequestBody AuthReqVO authReqVO);

}
