package com.example.serviceorder.service;

import com.example.serviceorder.service.bo.OrderReqBO;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:34
 **/
public interface OrderService {

    boolean createOrder(OrderReqBO orderReqBO);

}
