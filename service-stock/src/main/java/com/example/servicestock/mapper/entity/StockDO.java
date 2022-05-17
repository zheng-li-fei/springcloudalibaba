package com.example.servicestock.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "goods")
public class StockDO {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField(value = "goods_id")
    String goodsId;

    @TableField(value = "stock")
    Integer stock;

    @TableField(value = "create_time")
    Date createTime;

    @TableField(value = "update_time")
    Date updateTime;

    @TableField(exist = false)
    Integer operatorStockNum;

}