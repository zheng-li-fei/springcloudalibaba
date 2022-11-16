package com.zlf.serverorder.config.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zlf.api.commonapiorder.vo.OrderReqVO;
import com.zlf.commonbase.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: @SentinelResource注解的blockHandler只处理sentinel控制台的错误(流控: 时间->访问量 及 降级熔断: 时间->异常数|异常比例)，不能处理程序错误，程序错误会走fallback。
 * @author: zhenglifei
 * @create: 2022/5/14 15:25
 **/
@Slf4j
public class OrderBlockHandler {

    /**
     * 下单服务限流
     *
     * @param orderReqVO
     * @param blockException
     * @return
     */
    public static boolean createOrderBlockHandler(OrderReqVO orderReqVO, BlockException blockException) {
        log.error("service-order 服务被限流了 {},blockException {}",orderReqVO, blockException);
        throw new BizException("service-order 服务被限流了");
    }

}
