package com.zlf.api.commonapiauth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 注册请求参数
 * @author: zhenglifei
 * @create: 2022/4/19 11:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterReqVO {

    @NotNull(message = "请求来源平台类型")
    @ApiModelProperty(value = "平台类型: 10-商城, 20-工行", required = true)
    Integer platformType;

    @ApiModelProperty(value = "用户名", required = false)
    String userName;

    @ApiModelProperty(value = "手机号", required = false)
    String phone;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    String userPwd;
}
