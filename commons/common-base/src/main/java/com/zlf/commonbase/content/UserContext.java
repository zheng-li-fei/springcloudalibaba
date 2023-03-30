package com.zlf.commonbase.content;


import com.zlf.commonbase.model.AuthUser;

/**
 * @description: 用户上下文
 * @author: zhenglifei
 * @date: 2023/3/30 13:16
 */
public class UserContext {

    private static final ThreadLocal<AuthUser> USERS = new ThreadLocal<>();

    /**
     * 获取当前登录对象
     *
     * @return AuthUser
     */
    public static AuthUser getLoginUser() {
        return USERS.get();
    }

    /**
     * 设置当前登录对象
     *
     * @param user
     */
    public static void setLoginUser(AuthUser user) {
        USERS.set(user);
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static String getUserId() {
        return USERS.get().getUserId();
    }


    /**
     * 获取平台类型
     *
     * @return Integer
     */
    public static Integer getPlatformType() {
        return USERS.get().getPlatformType();
    }

    /**
     * 移除当前登录对象
     */
    public static void remove() {
        USERS.remove();
    }
}
