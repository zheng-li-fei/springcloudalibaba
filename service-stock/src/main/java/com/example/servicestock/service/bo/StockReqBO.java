package com.example.servicestock.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 11:49
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockReqBO {

    Integer id;

    String goodsId;

    /**
     * 新增/减少的库存数
     */
    Integer operatorStockNum;

}
