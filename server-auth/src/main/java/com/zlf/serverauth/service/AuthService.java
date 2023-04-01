package com.zlf.serverauth.service;


import com.zlf.api.commonapiauth.vo.req.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthLogoutReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthRegisterReqVO;
import com.zlf.api.commonapiauth.vo.req.AuthUserListReqVO;
import com.zlf.api.commonapiauth.vo.res.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.res.AuthUserListResVO;
import com.zlf.commonbase.model.base.PageQueryResponse;
import com.zlf.serverauth.enums.PlatformTypeEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @description: 登录注册服务
 * @author: zhenglifei
 * @create: 2022/4/19 10:34
 **/
@Validated
public interface AuthService {

    /**
     * 获取当前实现类的认证类型
     * @return  LoginTypeEnum
     */
    PlatformTypeEnum getPlatformType();

    /**
     * 注册
     * @param registerReqVO 请求参数
     * @return  Boolean
     */
    Boolean authRegister(@Valid @NotNull AuthRegisterReqVO registerReqVO);

    /**
     * 登录
     * @param loginReqVO    请求参数
     * @return  AuthLoginResVO
     */
    AuthLoginResVO authLogin(@Valid @NotNull AuthLoginReqVO loginReqVO);

    PageQueryResponse<AuthUserListResVO> getUserListByPage(AuthUserListReqVO reqVO);

    /**
     * 退出
     * @param loginOutReqVO 请求参数
     * @return  Boolean
     */
    Boolean authLoginOut(@Valid @NotNull AuthLogoutReqVO loginOutReqVO);


}
