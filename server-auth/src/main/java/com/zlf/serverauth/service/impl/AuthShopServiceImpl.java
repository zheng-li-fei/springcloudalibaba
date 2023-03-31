package com.zlf.serverauth.service.impl;

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
import com.zlf.commonbase.constant.RedisKeyConstant;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.model.AuthUser;
import com.zlf.serverauth.dao.AuthDao;
import com.zlf.serverauth.dao.entity.AuthDO;
import com.zlf.serverauth.enums.PlatformTypeEnum;
import com.zlf.serverauth.enums.error.AuthErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @description: 商城实现类
 * @author: zhenglifei
 * @create: 2022/4/19 10:35
 **/
@Slf4j
@Service
public class AuthShopServiceImpl extends AbstractAuthService {

    @Autowired
    AuthDao authDao;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public PlatformTypeEnum getPlatformType() {
        return PlatformTypeEnum.SHOP;
    }

    @Override
    public Boolean authRegister(AuthRegisterReqVO registerReqVO) {
        Integer platformType = registerReqVO.getPlatformType();
        String userName = registerReqVO.getUserName();
        String userPwd = registerReqVO.getUserPwd();

        //用户名，手机号唯一性校验
        Integer userCount = authDao.selectCount(new LambdaQueryWrapper<AuthDO>()
                .eq(AuthDO::getUserName, userName)
                .eq(AuthDO::getPlatformType, platformType));
        if (userCount > 0) {
            log.error("用户注册,用户名已存在 platformType {}, userName {}", PlatformTypeEnum.getDescByType(platformType), userName);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_USER_EXIST);
        }

        //前端密码解密后的明文
        String decodePassword;
        try {
            decodePassword = SecureUtil.aes(CommonConstants.LOGIN_AES_KEY.getBytes()).decryptStr(userPwd);
        } catch (Exception e) {
            log.error("账户密码登录,密码解码失败! password {}", userPwd, e);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_DECODE_PWD_ERROR);
        }
        //密码加密
        String newPwd = BCrypt.hashpw(decodePassword, BCrypt.gensalt());

        AuthDO authDO = AuthDO.builder()
                .userId(IdUtil.fastSimpleUUID())
                .userName(userName)
                .nikeName("")
                .phone("")
                .userPwd(newPwd)
                .platformType(platformType)
                .lastLoginTime(LocalDateTime.now())
                .build();
        return authDao.insert(authDO) > 0;
    }

    @Override
    public AuthLoginResVO authLogin(AuthLoginReqVO loginReqVO) {
        Integer platformType = loginReqVO.getPlatformType();
        String userName = loginReqVO.getUserName();
        String userPwd = loginReqVO.getUserPwd();
        String lastLoginIp = loginReqVO.getLastLoginIp();

        //1.校验用户是否存在
        AuthDO authDO = authDao.selectOne(new LambdaQueryWrapper<AuthDO>()
                .eq(AuthDO::getUserName, userName)
                .eq(AuthDO::getPlatformType, platformType));
        if (authDO == null) {
            log.error("用户不存在 platformType {},userName {}", PlatformTypeEnum.getDescByType(platformType), userName);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_USER_OR_PWD_ERROR);
        }

        //前端密码解密后的明文
        String decodePassword;
        try {
            decodePassword = SecureUtil.aes(CommonConstants.LOGIN_AES_KEY.getBytes()).decryptStr(userPwd);
        } catch (Exception e) {
            log.error("账户密码登录,密码解码失败! password {}", userPwd, e);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_DECODE_PWD_ERROR);
        }
        //2.校验密码是否正确
        if (!BCrypt.checkpw(decodePassword, authDO.getUserPwd())) {
            log.error("密码错误 platformType {},userName {}", PlatformTypeEnum.getDescByType(platformType), userName);
            throw new BizException(AuthErrorEnum.SERVER_AUTH_USER_OR_PWD_ERROR);
        }

        String userId = authDO.getUserId();
        //3.生成token

        AuthDO authDoUpdate = new AuthDO();
        authDoUpdate.setId(authDO.getId());
        authDoUpdate.setLastLoginIp(lastLoginIp);
        authDao.updateById(authDoUpdate);

        AuthUser user = AuthUser.builder()
                .token("")
                .userId(userId)
                .userName(authDO.getUserName())
                .nikeName(authDO.getNikeName())
                .phone(authDO.getPhone())
                .platformType(platformType)
                .timestamp(System.currentTimeMillis())
                .build();
        String token = JWTUtil.createToken(JSONObject.from(user), CommonConstants.HMAC_KEY.getBytes());

        //生成redis缓存 userId -> token
        String redisKey = String.format(RedisKeyConstant.SERVER_AUTH_SHOP_LOGIN_USERID_STR, userId);
        //当前redis中存储有效的token,同一用户多次登录，只存最新生成的token
        redisTemplate.opsForValue().set(redisKey, token, 3, TimeUnit.DAYS);

        return AuthLoginResVO.builder()
                .userId(userId)
                .token(token)
                .platformType(platformType)
                .build();
    }

    @Override
    public Boolean authLoginOut(AuthLoginOutReqVO loginOutReqVO) {
        String userId = loginOutReqVO.getUserId();
        //删除redis缓存 userId -> token
        redisTemplate.delete(String.format(RedisKeyConstant.SERVER_AUTH_SHOP_LOGIN_USERID_STR, userId));
        return Boolean.TRUE;
    }

}
