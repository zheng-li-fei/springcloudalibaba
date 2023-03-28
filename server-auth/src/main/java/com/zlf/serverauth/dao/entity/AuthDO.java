package com.zlf.serverauth.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "orders")
public class AuthDO {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField(value = "create_time")
    Date createTime;

    @TableField(value = "update_time")
    Date updateTime;

}