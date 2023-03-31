package com.zlf.api.commonapiorder.feign;

import com.zlf.commonbase.utils.ResEx;
import com.zlf.api.commonapiorder.feign.fallback.OrderServiceFeignFallbackFactory;
import com.zlf.api.commonapiorder.vo.OrderReqVO;
import com.zlf.api.commonapicore.config.OpenFeignConfig;
import com.zlf.commonbase.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/11/9 16:50
 **/
@FeignClient(value = ServiceNameConstants.SERVER_ORDER , fallbackFactory = OrderServiceFeignFallbackFactory.class, configuration = OpenFeignConfig.class)
public interface OrderFeignClient {

    /**
     * 下单
     *
     * @param orderReqVO
     * @return
     */
    @RequestMapping(value = "/order/createOrder", method = RequestMethod.POST)
    ResEx createOrder(@RequestBody OrderReqVO orderReqVO);


}
