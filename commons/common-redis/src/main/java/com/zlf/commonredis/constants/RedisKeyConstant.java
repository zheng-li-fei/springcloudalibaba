package com.zlf.commonredis.constants;

/**
 * @description: redis key操作常量
 * @author: zhenglifei
 * @create: 2022/3/26 10:00
 **/
public class RedisKeyConstant {

    /**
     * 商城登录token前缀:userId
     */
    public static final String SERVER_AUTH_SHOP_LOGIN_USERID_STR = "server_auth:shop:login:userid_str:%s";

    /**
     * 限流接口key
     */
    //接口访问限流
    public static final String OPEN_INTERFACE_LIMIT_KEY_STR = "open:limit:appName_%s:%s_%s";
    //用户访问限流
    public static final String OPEN_USER_LIMIT_KEY_STR = "open:limit:appName_%s:%s_%s_userFlag_%s";
    //ip访问限流
    public static final String OPEN_IP_LIMIT_KEY_STR = "open:limit:appName_%s:%s_%s_remoteIp_%s";

}
