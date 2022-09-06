package com.example.servicestock.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.servicestock.config.GlobalBlockHandler;
import com.example.servicestock.controller.vo.StockReqVO;
import com.example.servicestock.service.StockService;
import com.example.servicestock.service.bo.StockReqBO;
import com.example.servicestock.service.bo.StockResBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:28
 **/
@Slf4j
@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @RestController
    public class DemoController {
        @GetMapping("/demo")
        public String demo() throws InterruptedException {
            //模拟业务耗时处理流程
            log.info("开始模拟线程执行");
            Thread.sleep(12 * 1000L);
            log.info("结束模拟线程执行");
            return "hello";
        }
    }

    @SentinelResource(value = "addStock",blockHandler = "blockHandler",blockHandlerClass = GlobalBlockHandler.class)
    @RequestMapping(value = "/stock/addStock", method = RequestMethod.POST)
    public void addStock(@RequestBody StockReqVO stockReqVO) {
        StockReqBO stockReqBO = StockReqBO.builder().build();
        BeanUtil.copyProperties(stockReqVO, stockReqBO);
        stockService.addStock(stockReqBO);
    }

    @SentinelResource(value = "reduceStock")
    @RequestMapping(value = "/stock/reduceStock", method = RequestMethod.POST)
    public void reduceStock(@RequestBody StockReqVO stockReqVO) {
        StockReqBO stockReqBO = StockReqBO.builder().build();
        BeanUtil.copyProperties(stockReqVO, stockReqBO);
        stockService.reduceStock(stockReqBO);
    }

    @RequestMapping(value = "/stock/existGoods", method = RequestMethod.GET)
    public StockResBO existGoods(@RequestParam String goodsId) {
       return stockService.existGoods(goodsId);
    }

}
