package com.example.serviceorder.servicefeign.stock.feignVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
