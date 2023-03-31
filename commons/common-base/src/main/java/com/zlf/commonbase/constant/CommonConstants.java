package com.zlf.commonbase.constant;

/**
 * 全局公共常量
 *
 * @author zhenglifei
 * @date 2018/10/29
 */
public interface CommonConstants {

    /**
     * token请求头名称
     */
    String AUTHORIZATION = "authorization";
    /**
     * 通过网关校验时间戳
     */
    String GATEWAY_CHECK_TIMESTAMP = "gateway_check_timestamp";

    String BEARER_TYPE = "Bearer";

    /**
     * JWTUtil key
     */
    String HMAC_KEY = "51131f501ffe43afb73c81d348d757ca";

    /**
     * 登录AES加密密码
     */
    String LOGIN_AES_KEY = "ptkf4ewzutk0leydzf35ia3gderz2ecs";

}
