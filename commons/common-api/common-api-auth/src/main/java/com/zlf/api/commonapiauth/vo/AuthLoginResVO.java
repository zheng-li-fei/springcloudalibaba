package com.zlf.api.commonapiauth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @description: 登录响应参数
 * @author: zhenglifei
 * @create: 2022/4/19 11:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginResVO {

    @ApiModelProperty(value = "用户id")
    String userId;

    @ApiModelProperty(value = "生成的认证凭证")
    String token;

    @ApiModelProperty(value = "平台类型: 10-商城, 20-工行")
    Integer platformType;

}
