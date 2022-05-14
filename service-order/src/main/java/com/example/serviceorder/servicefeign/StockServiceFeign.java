package com.example.serviceorder.servicefeign;

import com.example.serviceorder.servicefeign.feignVO.StockReqVO;
import com.example.serviceorder.servicefeign.feignVO.StockResBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 调用远程的库存服务
 * @author: zhenglifei
 * @create: 2022/4/19 15:16
 *
 * name:指定调用rest接口所对应的服务名
 **/
@FeignClient(value = "service-stock",fallback = StockServiceFeignFallback.class)
public interface StockServiceFeign {

    @RequestMapping(value = "/stock/addStock", method = RequestMethod.POST)
    void addStock(@RequestBody StockReqVO stockReqVO);

    @RequestMapping(value = "/stock/reduceStock", method = RequestMethod.POST)
    void reduceStock(@RequestBody StockReqVO stockReqVO);

    @RequestMapping(value = "/stock/existGoods", method = RequestMethod.GET)
    StockResBO existGoods(@RequestParam String goodsId);


}
