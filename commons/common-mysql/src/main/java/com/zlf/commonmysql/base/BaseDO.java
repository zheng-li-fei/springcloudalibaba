package com.zlf.commonmysql.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: zhenglifei
 * @date: 2023/3/29 16:10
 */
@Data
public class BaseDO {
    @TableField(value = "create_time")
    Date createTime;

    @TableField(value = "create_user_id")
    String createUserId;

    @TableField(value = "create_user_id")
    String createUserName;

    @TableField(value = "update_time")
    Date updateTime;

    @TableField(value = "update_user_id")
    String updateUserId;

    @TableField(value = "update_user_id")
    String updateUserName;

    @TableField(value = "version")
    Integer version;

    @TableField(value = "deleted")
    Boolean deleted;
}
