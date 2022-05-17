package com.example.serviceorder.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "orders")
public class OrderDO {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField(value = "goods_id")
    String goodsId;

    @TableField(value = "goods_name")
    String goodsName;

    @TableField(value = "order_num")
    Integer orderNum;

    @TableField(value = "create_time")
    Date createTime;

    @TableField(value = "update_time")
    Date updateTime;

}