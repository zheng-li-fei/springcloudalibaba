package com.zlf.serverorder.service;

import com.zlf.serverorder.service.bo.OrderReqBO;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:34
 **/
public interface OrderService {

    boolean createOrder(OrderReqBO orderReqBO);

}
