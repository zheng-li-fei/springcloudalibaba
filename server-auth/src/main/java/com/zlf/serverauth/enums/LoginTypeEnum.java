package com.zlf.serverauth.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 登录类型枚举: namePwd: 用户名密码登录, phonePwd: 手机号密码登录, phoneCode: 手机号短信验证码登录, wechat: 微信
 * @author: zhenglifei
 * @date: 2023/3/29 15:15
 */
@Getter
public enum LoginTypeEnum {

    /**
     * 101: 用户名密码登录
     */
    NAME_PWD("namePwd", true, false, false, false, "用户名密码登录"),
    /**
     * 111: 手机号密码登录
     */
    PHONE_PWD("phonePwd", false, true, false, false, "手机号密码登录"),
    /**
     * 121: 手机号短信验证码登录
     */
    PHONE_CODE("phoneCode", false, false, true, false, "手机号短信验证码登录"),
    /**
     * 微信登录
     */
    WECHAT("wechat", false, false, true, false, "微信登录"),

    ;

    private String type;
    /**
     * 是否为账号密码登录
     */
    private boolean accountPasswordLogin;
    /**
     * 是否为手机号密码登录
     */
    private boolean phonePasswordLogin;
    /**
     * 是否为手机号验证码登录
     */
    private boolean phoneCodeLogin;
    /**
     * 是否为第三方登录
     */
    private boolean thirdLogin;
    private String desc;

    /**
     * 构造器
     *
     * @param type                 类型
     * @param accountPasswordLogin 账号密码登录
     * @param phonePasswordLogin   手机号密码登录
     * @param phoneCodeLogin       手机号验证码登录
     * @param thirdLogin           第三方登录
     * @param desc                 描述
     */
    LoginTypeEnum(String type, boolean accountPasswordLogin, boolean phonePasswordLogin, boolean phoneCodeLogin, boolean thirdLogin, String desc) {
        this.type = type;
        this.accountPasswordLogin = accountPasswordLogin;
        this.phonePasswordLogin = phonePasswordLogin;
        this.phoneCodeLogin = phoneCodeLogin;
        this.thirdLogin = thirdLogin;
        this.desc = desc;
    }

    /**
     * 校验登录类型是否合法
     *
     * @param type
     * @return
     */
    public static boolean checkType(String type) {
        if (type == null) {
            return false;
        }
        for (LoginTypeEnum typeEnum : LoginTypeEnum.values()) {
            if (StringUtils.equals(typeEnum.getType(), type)) {
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
    public static LoginTypeEnum getLoginTypeEnumByType(String type) {
        if (type == null) {
            return null;
        }
        for (LoginTypeEnum typeEnum : LoginTypeEnum.values()) {
            if (StringUtils.equals(typeEnum.getType(), type)) {
                return typeEnum;
            }
        }
        return null;
    }

    /**
     * 根据登录类型获取描述
     *
     * @param type
     * @return
     */
    public static String getDescByType(String type) {
        if (type == null) {
            return "";
        }
        for (LoginTypeEnum typeEnum : LoginTypeEnum.values()) {
            if (StringUtils.equals(typeEnum.getType(), type)) {
                return typeEnum.getDesc();
            }
        }
        return "";
    }

}
