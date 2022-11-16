package com.zlf.serverorder.config.sentinel;

import com.zlf.api.commonapiorder.vo.OrderReqVO;
import com.zlf.commonbase.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: @SentinelResource注解的blockHandler只处理sentinel控制台的错误，不能处理程序错误，程序错误会走fallback。
 * @author: zhenglifei
 * @create: 2022/5/14 15:25
 **/
@Slf4j
public class OrderFallbackClass {

    /**
     * 下单服务降级
     *
     * @param orderReqVO
     * @param throwable
     * @return
     */
    public static boolean createOrderFallback(OrderReqVO orderReqVO, Throwable throwable) {
        log.error("service-order 服务被降级了 {} , throwable {}",orderReqVO, throwable);
        throw new BizException("service-order 服务被降级了 ");
    }

}
