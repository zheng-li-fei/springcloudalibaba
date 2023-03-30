package com.zlf.commonbase.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 存储在缓存中的对象
 * @author: zhenglifei
 * @date: 2023/3/30 10:13
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUser {

    @ApiModelProperty(value = "token")
    String token;

    @ApiModelProperty(value = "用户id")
    String userId;

    @ApiModelProperty(value = "用户名称")
    String userName;

    @ApiModelProperty(value = "昵称")
    String nikeName;

    @ApiModelProperty(value = "手机号")
    String phone;

    @ApiModelProperty(value = "平台类型: 10-商城, 20-工行")
    Integer platformType;

    @ApiModelProperty(value = "时间戳")
    Long timestamp;

}
