package com.zlf.serverstock.service;

import com.zlf.api.commonapistock.bo.StockReqBO;
import com.zlf.api.commonapistock.bo.StockResBO;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 11:41
 **/
public interface StockService {

    boolean addStock(StockReqBO stockReqBO);

    boolean reduceStock(StockReqBO stockReqBO);

    StockResBO existGoods(String goodsId);

}
