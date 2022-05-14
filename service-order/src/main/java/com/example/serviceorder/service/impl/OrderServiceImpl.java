package com.example.serviceorder.service.impl;

import com.example.common.exception.BizException;
import com.example.serviceorder.mapper.OrderDao;
import com.example.serviceorder.service.OrderService;
import com.example.serviceorder.service.bo.OrderReqBO;
import com.example.serviceorder.servicefeign.StockServiceFeign;
import com.example.serviceorder.servicefeign.feignVO.StockReqVO;
import com.example.serviceorder.servicefeign.feignVO.StockResBO;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
    StockServiceFeign stockServiceFeign;


    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean createOrder(OrderReqBO orderReqBO) {
        if (orderReqBO == null || StringUtils.isBlank(orderReqBO.getGoodsName()) || orderReqBO.getOrderNum() == null) {
            throw new BizException("订单参数错误");
        }

        //1.判断商品是否存在
        StockResBO stockResBO = stockServiceFeign.existGoods(orderReqBO.getGoodsId());
        if (stockResBO == null || StringUtils.isBlank(stockResBO.getGoodsId())) {
            throw new BizException("商品不存在");
        }

        //2.扣减库存
        StockReqVO stockReqVO = StockReqVO.builder().build();
        stockReqVO.setGoodsId(orderReqBO.getGoodsId());
        stockReqVO.setOperatorStockNum(orderReqBO.getOrderNum());
        stockServiceFeign.reduceStock(stockReqVO);

        //3.下单
         orderDao.insert(orderReqBO) ;

//         throw new BizException("模拟分布式事务失败");
        return true;
    }
}
