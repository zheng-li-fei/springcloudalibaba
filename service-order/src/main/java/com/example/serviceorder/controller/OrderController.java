package com.example.serviceorder.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.common.config.log.SysLogAnnotation;
import com.example.serviceorder.config.GlobalBlockHandler;
import com.example.serviceorder.controller.vo.OrderReqVO;
import com.example.serviceorder.service.OrderService;
import com.example.serviceorder.service.bo.OrderReqBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 下单
     *
     * @param orderReqVO
     * @return
     */
    @SysLogAnnotation(value = "下单 createOrder 日志拦截测试")
    @SentinelResource(value = "createOrder", blockHandler = "blockHandler", blockHandlerClass = GlobalBlockHandler.class)
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
