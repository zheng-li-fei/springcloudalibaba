package com.example.servicestock.controller.vo;

import lombok.*;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 11:43
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockReqVO {

    /**
     * 商品id
     */
    String goodsId;

    /**
     * 当前库存数
     */
    Integer stock;

    /**
     * 操作的库存数
     */
    Integer operatorStockNum;

}
