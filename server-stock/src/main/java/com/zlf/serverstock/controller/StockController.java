package com.zlf.serverstock.controller;


import cn.hutool.core.bean.BeanUtil;
import com.zlf.api.commonapistock.bo.StockReqBO;
import com.zlf.api.commonapistock.bo.StockResBO;
import com.zlf.api.commonapistock.feign.StockFeignClient;
import com.zlf.api.commonapistock.vo.StockReqVO;

import com.zlf.serverstock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:28
 **/
@Slf4j
@RestController
public class StockController implements StockFeignClient {

    @Autowired
    StockService stockService;

    @Override
    public ResEx<Boolean> addStock(StockReqVO stockReqVO) {
        StockReqBO stockReqBO = StockReqBO.builder().build();
        BeanUtil.copyProperties(stockReqVO, stockReqBO);
        stockService.addStock(stockReqBO);
        return ResEx.success();
    }

    @Override
    public ResEx<Boolean> reduceStock(StockReqVO stockReqVO) {
        StockReqBO stockReqBO = StockReqBO.builder().build();
        BeanUtil.copyProperties(stockReqVO, stockReqBO);
        stockService.reduceStock(stockReqBO);
        return ResEx.success();
    }

    @Override
    public ResEx<StockResBO> existGoods(String goodsId) {
        return ResEx.success(stockService.existGoods(goodsId));
    }
}
