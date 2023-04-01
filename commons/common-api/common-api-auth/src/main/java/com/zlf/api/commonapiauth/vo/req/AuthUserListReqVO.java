package com.zlf.api.commonapiauth.vo.req;

import com.zlf.commonbase.model.base.PageQueryParameters;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户列表请求参数
 * @author: zhenglifei
 * @create: 2022/4/19 11:43
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserListReqVO extends PageQueryParameters {

    @ApiModelProperty(value = "用户名", required = false)
    String userName;

    @ApiModelProperty(value = "手机号", required = false)
    String phone;

    @ApiModelProperty(value = "平台类型: 10-商城,20-工行", required = false, hidden = true)
    Integer platformType;

}
