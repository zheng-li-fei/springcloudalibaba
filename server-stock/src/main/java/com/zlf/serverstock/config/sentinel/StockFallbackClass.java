package com.zlf.serverstock.config.sentinel;

import com.zlf.api.commonapistock.bo.StockReqBO;
import com.zlf.baseproject.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: @SentinelResource注解的blockHandler只处理sentinel控制台的错误，不能处理程序错误，程序错误会走fallback。
 * @author: zhenglifei
 * @create: 2022/5/14 15:25
 **/
@Slf4j
public class StockFallbackClass {

    /**
     * @param stockReqBO
     * @param throwable
     * @return
     */
    public static boolean addStockFallback(StockReqBO stockReqBO, Throwable throwable) {
        log.error("service-stock 服务被降级了 {},throwable {}", stockReqBO, throwable);
        throw new BizException("service-stock 服务被降级了");
    }

    /**
     * @param stockReqBO
     * @param throwable
     * @return
     */
    public static boolean reduceStockFallback(StockReqBO stockReqBO, Throwable throwable) {
        log.error("service-stock 服务被降级了 stockReqBO {},throwable {}", stockReqBO, throwable);
        throw new BizException("service-stock 服务被降级了");
    }

}
