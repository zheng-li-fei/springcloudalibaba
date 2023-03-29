package com.zlf.commonbase.constant;

/**
 * 全局公共常量
 *
 * @author zlt
 * @date 2018/10/29
 */
public interface CommonConstants {

    /**
     * token请求头名称
     */
    String AUTHORIZATION = "authorization";

    /**
     * The access token issued by the authorization server. This value is REQUIRED.
     */
    String ACCESS_TOKEN = "access_token";

    String BEARER_TYPE = "Bearer";

    /**
     * JWTUtil key
     */
    String HMAC_KEY = "51131f501ffe43afb73c81d348d757ca";

}
