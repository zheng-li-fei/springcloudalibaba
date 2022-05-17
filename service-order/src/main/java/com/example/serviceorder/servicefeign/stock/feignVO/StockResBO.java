package com.example.serviceorder.servicefeign.stock.feignVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/24 9:54
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockResBO {

    Integer id;

    String goodsId;

    Integer stock;
}
