package com.zlf.api.commonapiauth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @description: 退出登录请求参数
 * @author: zhenglifei
 * @create: 2022/4/19 11:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginOutReqVO {

    @NotNull(message = "请求来源平台类型")
    @ApiModelProperty(value = "平台类型: 10-商城, 20-工行", required = true)
    Integer platformType;

    @ApiModelProperty(value = "用户id", required = false, hidden = true)
    String userId;

}
