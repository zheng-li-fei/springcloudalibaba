package com.zlf.serverorder.controller;

import cn.hutool.core.bean.BeanUtil;

import com.zlf.api.commonapiorder.feign.OrderFeignClient;
import com.zlf.api.commonapiorder.vo.OrderReqVO;
import com.zlf.commonbase.utils.ResEx;
import com.zlf.serverorder.service.OrderService;
import com.zlf.serverorder.service.bo.OrderReqBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:27
 **/
@Slf4j
@RestController
public class OrderController implements OrderFeignClient {

    @Autowired
    OrderService orderService;

    /**
     * 下单
     * @param orderReqVO
     * @return
     */
    @Override
    public ResEx createOrder(OrderReqVO orderReqVO) {
        //全局事务注解？？？
        //1.新增订单
        OrderReqBO orderReqBO = OrderReqBO.builder().build();
        BeanUtil.copyProperties(orderReqVO, orderReqBO);
        orderService.createOrder(orderReqBO);
        return ResEx.success();
    }
}
