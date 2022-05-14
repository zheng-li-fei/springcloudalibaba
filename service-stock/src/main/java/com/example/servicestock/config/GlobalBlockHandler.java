package com.example.servicestock.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @description: @SentinelResource注解的blockHandler只处理sentinel控制台的错误，不能处理程序错误，程序错误会走fallback。
 * @author: zhenglifei
 * @create: 2022/5/14 15:25
 **/
public class GlobalBlockHandler {

    public static String blockHandler(BlockException exception){
        return "service-stock 服务被限流了";
    }

}
