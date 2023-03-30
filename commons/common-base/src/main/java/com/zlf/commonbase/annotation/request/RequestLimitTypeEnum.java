package com.zlf.commonbase.annotation.request;

/**
 * @description: 限流类型，每秒的访问次数
 * @author: zhenglifei
 * @create: 2021/8/18 16:05
 **/
public enum RequestLimitTypeEnum {

    /**
     * 接口访问限流 , 应用名:类名_方法名
     */
    INTERFACE(1, "接口访问限流"),

    /**
     * 用户访问限流 , 应用名:类名_方法名_userFlag_用户唯一标识
     */
    USER(2, "用户访问限流"),

    /**
     * ip访问限流 , 应用名:类名_方法名_remoteIp_客户端ip
     */
    IP(3, "ip访问限流"),
    ;


    private int type;
    private String desc;

    RequestLimitTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
