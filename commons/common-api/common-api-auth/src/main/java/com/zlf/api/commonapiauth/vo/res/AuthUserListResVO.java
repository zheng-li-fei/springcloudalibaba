package com.zlf.api.commonapiauth.vo.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description: 用户列表响应数据
 * @author: zhenglifei
 * @create: 2022/4/19 11:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserListResVO {

    @ApiModelProperty(value = "用户id")
    String userId;

    @ApiModelProperty(value = "用户名称")
    String userName;

    @ApiModelProperty(value = "用户昵称")
    String nikeName;

    @ApiModelProperty(value = "手机号")
    String phone;

    /**
     * 平台类型: 10-商城, 20-工行
     */
    @ApiModelProperty(value = "平台类型: 10-商城, 20-工行")
    Integer platformType;

    @ApiModelProperty(value = "上次登录ip")
    String lastLoginIp;

    @ApiModelProperty(value = "上次登录时间")
    LocalDateTime lastLoginTime;

}
