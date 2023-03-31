package com.zlf.api.commonapiorder.feign.fallback;

import com.zlf.commonbase.utils.ResEx;
import com.zlf.api.commonapiorder.feign.OrderFeignClient;
import com.zlf.api.commonapiorder.vo.OrderReqVO;
import com.zlf.api.commonapicore.enums.CommonApiErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;


/**
 * @description: 服务不可用时，降级处理
 * @author: zhenglifei
 * @create: 2022/4/20 9:17
 **/
@Slf4j
@Service
public class OrderServiceFeignFallbackFactory implements FallbackFactory<OrderFeignClient> {

    @Override
    public OrderFeignClient create(Throwable throwable) {
        return new OrderFeignClient() {
            @Override
            public ResEx createOrder(OrderReqVO orderReqVO) {
                log.error("OrderFeignClient 添加库存方法,当前服务不可用,触发服务降级 orderReqVO {}", orderReqVO, throwable);
                return ResEx.error(CommonApiErrorEnum.SERVICE_NOT_EXIST);
            }
        };
    }
}



