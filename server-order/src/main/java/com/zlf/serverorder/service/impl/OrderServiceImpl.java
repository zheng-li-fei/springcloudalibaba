package com.zlf.serverorder.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.zlf.api.commonapistock.bo.StockResBO;

import com.zlf.api.commonapistock.feign.StockFeignClient;
import com.zlf.api.commonapistock.vo.StockReqVO;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.utils.ResEx;
import com.zlf.serverorder.config.sentinel.OrderBlockHandler;
import com.zlf.serverorder.config.sentinel.OrderFallbackClass;
import com.zlf.serverorder.dao.OrderDao;
import com.zlf.serverorder.service.OrderService;
import com.zlf.serverorder.service.bo.OrderReqBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:35
 **/
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Resource
    StockFeignClient stockFeignClient;

    @Override
    @SentinelResource(value = "createOrder",
            blockHandler = "createOrderBlockHandler", blockHandlerClass = OrderBlockHandler.class,
            fallback = "createOrderFallback", fallbackClass = OrderFallbackClass.class, exceptionsToIgnore = BizException.class)
    public boolean createOrder(OrderReqBO orderReqBO) {
        if (orderReqBO == null || StringUtils.isBlank(orderReqBO.getGoodsName()) || orderReqBO.getOrderNum() == null) {
            throw new BizException("订单参数错误");
        }

        //1.判断商品是否存在
        ResEx<StockResBO> stockResBO = stockFeignClient.existGoods(orderReqBO.getGoodsId());
        log.info("请求 existGoods 响应数据:{}", JSONObject.toJSONString(stockResBO));
        if (stockResBO.getCode() != ResEx.success().getCode()) {
            throw new BizException(stockResBO.getCode(),stockResBO.getMsg());
        }

        //2.扣减库存
        StockReqVO stockReqVO = StockReqVO.builder().build();
        stockReqVO.setGoodsId(orderReqBO.getGoodsId());
        stockReqVO.setOperatorStockNum(orderReqBO.getOrderNum());
        ResEx<Boolean> reduceFlag = stockFeignClient.reduceStock(stockReqVO);
        log.info("请求 reduceStock 响应数据:{}", JSONObject.toJSONString(reduceFlag));
        if (reduceFlag.getCode() != ResEx.success().getCode()) {
            throw new BizException(reduceFlag.getCode(),reduceFlag.getMsg());
        }
        //3.下单
        orderDao.insert(orderReqBO);
        //throw new BizException("模拟分布式事务失败");
        return true;
    }
}
