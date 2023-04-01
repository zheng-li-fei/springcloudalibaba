package com.zlf.serverauth.config.sentinel;

import com.zlf.api.commonapiauth.vo.req.AuthRegisterReqVO;
import com.zlf.commonbase.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: @SentinelResource注解的blockHandler只处理sentinel控制台的错误，不能处理程序错误，程序错误会走fallback。
 * @author: zhenglifei
 * @create: 2022/5/14 15:25
 **/
@Slf4j
public class AuthFallbackClass {

    /**
     * 服务降级
     *
     * @param registerReqVO
     * @param throwable
     * @return
     */
    public static boolean authRegisterFallback(AuthRegisterReqVO registerReqVO, Throwable throwable) {
        log.error("service-auth 服务被降级了 {} , throwable {}",registerReqVO, throwable);
        throw new BizException("service-auth 服务被降级了 ");
    }

}
