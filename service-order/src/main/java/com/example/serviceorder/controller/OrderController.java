package com.example.serviceorder.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.serviceorder.controller.vo.OrderReqVO;
import com.example.serviceorder.service.OrderService;
import com.example.serviceorder.service.bo.OrderReqBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:27
 **/
@Slf4j
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/createOrder", method = RequestMethod.POST)
    public boolean createOrder(@RequestBody OrderReqVO orderReqVO) {
        //全局事务注解？？？
        //1.新增订单
        OrderReqBO orderReqBO = OrderReqBO.builder().build();
        BeanUtil.copyProperties(orderReqVO, orderReqBO);
        orderService.createOrder(orderReqBO);
        return true;
    }

}
