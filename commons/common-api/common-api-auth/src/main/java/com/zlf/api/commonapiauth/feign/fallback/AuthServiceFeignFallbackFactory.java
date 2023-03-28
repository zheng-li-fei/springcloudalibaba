package com.zlf.api.commonapiauth.feign.fallback;

import com.zlf.api.commonapiauth.feign.AuthFeignClient;
import com.zlf.api.commonapiauth.vo.AuthReqVO;
import com.zlf.commonapicore.enums.CommonApiErrorEnum;
import com.zlf.commonbase.utils.ResEx;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @description: 服务不可用时，降级处理,返回自定义数据
 * @author: zhenglifei
 * @create: 2022/4/20 9:17
 **/
@Slf4j
@Service
public class AuthServiceFeignFallbackFactory implements FallbackFactory<AuthFeignClient> {

    @Override
    public AuthFeignClient create(Throwable throwable) {
        return new AuthFeignClient() {
            @Override
            public ResEx<Boolean> authRegister(AuthReqVO authReqVO) {
                log.error("AuthFeignClient authRegister,当前服务不可用,触发服务降级 authReqVO {}", authReqVO, throwable);
                return ResEx.error(CommonApiErrorEnum.SERVICE_NOT_EXIST);
            }

            @Override
            public ResEx<Boolean> authLogin(AuthReqVO authReqVO) {
                log.error("AuthFeignClient authLogin,当前服务不可用,触发服务降级 authReqVO {}", authReqVO, throwable);
                return ResEx.error(CommonApiErrorEnum.SERVICE_NOT_EXIST);
            }

            @Override
            public ResEx<Boolean> authLoginOut(AuthReqVO authReqVO) {
                log.error("AuthFeignClient authLoginOut,当前服务不可用,触发服务降级 authReqVO {}", authReqVO, throwable);
                return ResEx.error(CommonApiErrorEnum.SERVICE_NOT_EXIST);
            }
        };
    }
}
