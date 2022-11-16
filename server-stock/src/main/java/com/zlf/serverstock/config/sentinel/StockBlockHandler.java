package com.zlf.serverstock.config.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zlf.api.commonapistock.bo.StockReqBO;
import com.zlf.baseproject.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: @SentinelResource注解的blockHandler只处理sentinel控制台的错误(流控: 时间->访问量 及 降级熔断: 时间->异常数|异常比例)，不能处理程序错误，程序错误会走fallback。
 * @author: zhenglifei
 * @create: 2022/5/14 15:25
 **/
@Slf4j
public class StockBlockHandler {

    public static boolean addStockBlockHandler(StockReqBO stockReqBO, BlockException exception) {
        log.error("service-stock 服务被限流了 {}, exception {}",stockReqBO,exception);
        throw new BizException("service-stock 服务被限流了");
    }

    public static boolean reduceStockBlockHandler(StockReqBO stockReqBO, BlockException exception) {
        log.error("service-stock 服务被限流了 {}, exception {}",stockReqBO,exception);
        throw new BizException("service-stock 服务被限流了");
    }

}
