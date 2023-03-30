package com.zlf.commonbase.annotation.request;

/**
 * @description: 限流时间单位
 * @author: zhenglifei
 * @create: 2021/8/18 16:05
 **/
public enum RequestLimitTimeUnitEnum {

    /**
     * 秒
     */
    SECOND(1000, "秒"),

    /**
     * 分钟
     */
    MINUTE(60 * 1000, "分钟"),

    /**
     * 小时
     */
    HOUR(3600 * 1000, "小时"),
    ;

    //毫秒
    private int timeWindowLength;
    private String desc;

    RequestLimitTimeUnitEnum(int timeWindowLength, String desc) {
        this.timeWindowLength = timeWindowLength;
        this.desc = desc;
    }

    public int getTimeWindowLength() {
        return timeWindowLength;
    }

    public String getDesc() {
        return desc;
    }

}
