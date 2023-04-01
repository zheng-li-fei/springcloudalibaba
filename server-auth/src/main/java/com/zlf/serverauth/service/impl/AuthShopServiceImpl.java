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
import com.zlf.commonbase.constant.redis.RedisKeyConstant;
import com.zlf.commonbase.constant.redis.RedissionConstantKey;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonbase.model.AuthUser;
import com.zlf.serverauth.dao.AuthDao;
import com.zlf.serverauth.dao.entity.AuthDO;
import com.zlf.serverauth.enums.LoginTypeEnum;
import com.zlf.serverauth.enums.PlatformTypeEnum;
import com.zlf.serverauth.enums.error.AuthErrorEnum;
import com.zlf.serverauth.utils.RedissionLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
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

        RLock lock = RedissionLockUtil.lock(String.format(RedissionConstantKey.SERVER_AUTH_LOCK_PLATFORM_REGISTER, platformType));
        try {
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
        } finally {
            lock.unlock();
        }
    }

    /**
     * 校验登录参数
     *
     * @param loginTypeEnum 登录类型
     * @param loginReqVO    请求参数
     */
    @Override
    protected void validLoginParam(LoginTypeEnum loginTypeEnum, AuthLoginReqVO loginReqVO) {

        if(loginReqVO ==null){
            log.error("账号密码登录,请求参数为空");
            throw new BizException(AuthErrorEnum.SERVER_AUTH_REQ_PARAM_NOT_FOUND);
        }

        if (loginTypeEnum.isAccountPasswordLogin()) {
            //账号+密码
            if(StringUtils.isBlank(loginReqVO.getUserName())){
                log.error("账号密码登录,用户名为空");
                throw new BizException(AuthErrorEnum.SERVER_AUTH_REQ_USERNAME_NOT_FOUND);
            }
            if(StringUtils.isBlank(loginReqVO.getUserPwd())){
                log.error("账号密码登录,密码为空");
                throw new BizException(AuthErrorEnum.SERVER_AUTH_REQ_PWD_NOT_FOUND);
            }
        } else if (loginTypeEnum.isPhonePasswordLogin()) {
            //手机号+密码
            if(StringUtils.isBlank(loginReqVO.getPhone())){
                log.error("手机号密码登录,手机号为空");
                throw new BizException(AuthErrorEnum.SERVER_AUTH_REQ_PHONE_NOT_FOUND);
            }
            if(StringUtils.isBlank(loginReqVO.getUserPwd())){
                log.error("手机号密码登录,密码为空");
                throw new BizException(AuthErrorEnum.SERVER_AUTH_REQ_PWD_NOT_FOUND);
            }
        } else if (loginTypeEnum.isPhoneCodeLogin()) {
            //手机号+验证码
            if(StringUtils.isBlank(loginReqVO.getPhone())){
                log.error("手机号验证码登录,手机号为空");
                throw new BizException(AuthErrorEnum.SERVER_AUTH_REQ_PHONE_NOT_FOUND);
            }
            if(StringUtils.isBlank(loginReqVO.getCode())){
                log.error("手机号验证码登录,验证码为空");
                throw new BizException(AuthErrorEnum.SERVER_AUTH_REQ_CODE_NOT_FOUND);
            }
        } else if (loginTypeEnum.isThirdLogin()) {
            //第三方登录

        } else {
            throw new BizException("暂未支持");
        }
    }

    /**
     * 账号+密码登录
     *
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getAccountPasswordLoginInfo(AuthLoginReqVO loginReqVO) {
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
            throw new BizException(AuthErrorEnum.SERVER_AUTH_USER_OR_PWD_ERROR);
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

    /**
     * 手机号+密码登录
     *
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getPhonePasswordLoginInfo(AuthLoginReqVO loginReqVO) {
        return null;
    }

    /**
     * 手机号+验证码
     *
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getPhoneCodeLoginInfo(AuthLoginReqVO loginReqVO) {
        return null;
    }

    /**
     * 第三方登录
     *
     * @param loginReqVO 请求参数
     * @return
     */
    @Override
    protected AuthLoginResVO getThirdLoginInfo(AuthLoginReqVO loginReqVO) {
        return null;
    }

    @Override
    public AuthLoginResVO authLogin(AuthLoginReqVO loginReqVO) {
        return super.authLogin(loginReqVO);
    }

    @Override
    public Boolean authLoginOut(AuthLoginOutReqVO loginOutReqVO) {
        String userId = loginOutReqVO.getUserId();
        //删除redis缓存 userId -> token
        redisTemplate.delete(String.format(RedisKeyConstant.SERVER_AUTH_SHOP_LOGIN_USERID_STR, userId));
        return Boolean.TRUE;
    }

}
