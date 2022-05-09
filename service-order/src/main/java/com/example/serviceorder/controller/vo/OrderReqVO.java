package com.example.serviceorder.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:50
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqVO {

    String goodsId;

    String goodsName;

    Integer orderNum;

}
