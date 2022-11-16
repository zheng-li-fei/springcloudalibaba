package com.zlf.api.commonapistock.feign.fallback;

import com.zlf.commonbase.utils.ResEx;
import com.zlf.api.commonapistock.bo.StockResBO;
import com.zlf.api.commonapistock.feign.StockFeignClient;
import com.zlf.api.commonapistock.vo.StockReqVO;

import com.zlf.commonapicore.enums.CommonApiErrorEnum;
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
public class StockServiceFeignFallbackFactory implements FallbackFactory<StockFeignClient> {

    @Override
    public StockFeignClient create(Throwable throwable) {
        return new StockFeignClient() {
            @Override
            public ResEx addStock(StockReqVO stockReqVO) {
                log.error("StockFeignClient addStock,当前服务不可用,触发服务降级 stockReqVO {}", stockReqVO, throwable);
                return ResEx.error(CommonApiErrorEnum.SERVICE_NOT_EXIST);
            }

            @Override
            public ResEx reduceStock(StockReqVO stockReqVO) {
                log.error("StockFeignClient reduceStock,当前服务不可用,触发服务降级 stockReqVO {}", stockReqVO, throwable);
                return ResEx.error(CommonApiErrorEnum.SERVICE_NOT_EXIST);
            }

            @Override
            public ResEx<StockResBO> existGoods(String goodsId) {
                log.error("StockFeignClient existGoods,当前服务不可用,触发服务降级 goodsId {}", goodsId, throwable);
                return ResEx.error(CommonApiErrorEnum.SERVICE_NOT_EXIST);
            }
        };
    }
}
