package com.zlf.api.commonapistock.feign;


import com.zlf.api.commonapistock.bo.StockResBO;
import com.zlf.api.commonapistock.feign.fallback.StockServiceFeignFallbackFactory;
import com.zlf.api.commonapistock.vo.StockReqVO;

import com.zlf.commonapicore.config.OpenFeignConfig;
import com.zlf.commonbase.constant.ServiceNameConstants;
import com.zlf.commonbase.utils.ResEx;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 库存服务
 * @author: zhenglifei
 * @create: 2022/11/9 17:06
 **/
@FeignClient(value = ServiceNameConstants.SERVER_STOCK, fallbackFactory = StockServiceFeignFallbackFactory.class, configuration = OpenFeignConfig.class)
public interface StockFeignClient {

    /**
     * 添加库存
     *
     * @param stockReqVO
     */
    @RequestMapping(value = "/stock/addStock", method = RequestMethod.POST)
    ResEx<Boolean> addStock(@RequestBody StockReqVO stockReqVO);

    /**
     * 减少库存
     *
     * @param stockReqVO
     */
    @RequestMapping(value = "/stock/reduceStock", method = RequestMethod.POST)
    ResEx<Boolean> reduceStock(@RequestBody StockReqVO stockReqVO);

    /**
     * 判断商品是否存在
     *
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/stock/existGoods", method = RequestMethod.GET)
    ResEx<StockResBO> existGoods(@RequestParam String goodsId);

}
