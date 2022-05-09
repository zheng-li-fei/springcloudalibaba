package com.example.servicestock.service;

import com.example.servicestock.service.bo.StockReqBO;
import com.example.servicestock.service.bo.StockResBO;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 11:41
 **/
public interface StockService {

    void addStock(StockReqBO stockReqBO);

    void reduceStock(StockReqBO stockReqBO);

    StockResBO existGoods(String goodsId);

}
