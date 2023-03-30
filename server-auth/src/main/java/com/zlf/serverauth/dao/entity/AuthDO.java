package com.zlf.serverauth.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zlf.commonmysql.base.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zhenglifei
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "users")
public class AuthDO extends BaseDO {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField(value = "user_id")
    String userId;

    @TableField(value = "user_name")
    String userName;

    @TableField(value = "nike_name")
    String nikeName;

    @TableField(value = "phone")
    String phone;

    @TableField(value = "user_pwd")
    String userPwd;

    /**
     * 平台类型: 10-商城, 20-工行
     */
    @TableField(value = "platform_type")
    Integer platformType;

    @TableField(value = "last_login_ip")
    String lastLoginIp;

    @TableField(value = "last_login_time")
    LocalDateTime lastLoginTime;
}