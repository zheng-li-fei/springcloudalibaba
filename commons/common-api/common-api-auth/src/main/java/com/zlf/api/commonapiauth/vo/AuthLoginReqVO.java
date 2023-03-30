package com.zlf.api.commonapiauth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @description: 登录请求参数
 * @author: zhenglifei
 * @create: 2022/4/19 11:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginReqVO {

    @NotNull(message = "登录类型不能为空")
    @ApiModelProperty(value = "登录类型: 101: 用户名密码登录, 111: 手机号密码登录, 121: 手机号短信验证码登录", required = true)
    Integer loginType;

    @NotNull(message = "请求来源平台类型")
    @ApiModelProperty(value = "平台类型: 10-商城, 20-工行", required = true)
    Integer platformType;

    @ApiModelProperty(value = "用户名", required = false)
    String userName;

    @ApiModelProperty(value = "手机号", required = false)
    String phone;

    @ApiModelProperty(value = "密码", required = false)
    String userPwd;

    @ApiModelProperty(value = "短信验证码", required = false)
    String code;

    @ApiModelProperty(value = "登录ip", required = false, hidden = true)
    String lastLoginIp;

}
