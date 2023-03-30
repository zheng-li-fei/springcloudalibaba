package com.zlf.serverauth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zlf.api.commonapiauth.vo.AuthLoginOutReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginReqVO;
import com.zlf.api.commonapiauth.vo.AuthLoginResVO;
import com.zlf.api.commonapiauth.vo.AuthRegisterReqVO;
import com.zlf.commonbase.constant.CommonConstants;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.model.AuthUser;
import com.zlf.commonredis.constants.RedisKeyConstant;
import com.zlf.serverauth.dao.AuthDao;
import com.zlf.serverauth.dao.entity.AuthDO;
import com.zlf.serverauth.enums.PlatformTypeEnum;
import com.zlf.serverauth.enums.error.AuthErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @description: 工行实现类
 * @author: zhenglifei
 * @create: 2022/4/19 10:35
 **/
@Slf4j
@Service
public class AuthICBCServiceImpl extends AbstractAuthService {

    @Autowired
    AuthDao authDao;

    @Override
    public PlatformTypeEnum getPlatformType() {
        return PlatformTypeEnum.ICBC;
    }

    /**
     * 手机号 + 密码
     *
     * @param registerReqVO 请求参数
     * @return  Boolean
     */
    @Override
    public Boolean authRegister(AuthRegisterReqVO registerReqVO) {
        return Boolean.TRUE;
    }

    @Override
    public AuthLoginResVO authLogin(AuthLoginReqVO loginReqVO) {
        return AuthLoginResVO.builder().build();
    }

    @Override
    public Boolean authLoginOut(AuthLoginOutReqVO loginOutReqVO) {
        return Boolean.TRUE;
    }

}
