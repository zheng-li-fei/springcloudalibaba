package com.zlf.serverauth.enums;

import lombok.Getter;

/**
 * @description: 登录类型枚举: 101: 用户名密码登录, 111: 手机号密码登录, 121: 手机号短信验证码登录
 * @author: zhenglifei
 * @date: 2023/3/29 15:15
 */
@Getter
public enum LoginTypeEnum {

    /**
     * 101: 用户名密码登录
     */
    NAME_PWD(101,"用户名密码登录"),
    /**
     * 111: 手机号密码登录
     */
    PHONE_PWD(111,"手机号密码登录"),
    /**
     * 121: 手机号短信验证码登录
     */
    PHONE_CODE(121,"手机号短信验证码登录");

    private int type;
    private String desc;

    /**
     * 构造器
     * @param type  类型
     * @param desc  描述
     */
    LoginTypeEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    /**
     * 校验登录类型是否合法
     *
     * @param type
     * @return
     */
    public static boolean checkType(Integer type) {
        if (type == null) {
            return false;
        }
        for (LoginTypeEnum typeEnum : LoginTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取登录类型枚举
     *
     * @param type
     * @return
     */
    public static LoginTypeEnum getLoginTypeEnumByType(Integer type) {
        if (type == null) {
            return null;
        }
        for (LoginTypeEnum typeEnum : LoginTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum;
            }
        }
        return null;
    }

    /**
     * 根据登录类型获取描述
     * @param type
     * @return
     */
    public static String getDescByType(Integer type){
        if(type == null){
            return "";
        }
        for (LoginTypeEnum typeEnum : LoginTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum.getDesc();
            }
        }
        return "";
    }

}
