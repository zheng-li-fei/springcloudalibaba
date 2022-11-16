package com.zlf.commonbase.content;


import com.zlf.commonbase.model.LoginUser;

import java.util.Objects;

/**
 * @Desc: 用户上下文
 * @author: yangxingyao
 * @date: 2021/1/25 14:50
 * @warning：本内容仅限于浙江壹企通科技发展有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class UserContext {
    private static final ThreadLocal<LoginUser> user = new ThreadLocal<>();

    public static LoginUser getLoginUser() {
        return user.get();
    }

    public static void setLoginUser(LoginUser loginUser) {
        user.set(loginUser);
    }

    public static Long getUserId() {
        return Objects.isNull(getLoginUser()) ? null : (Objects.isNull(getLoginUser().getUser()) ? null : getLoginUser().getUser().getId());
    }

    public static Integer getShopId() {
        return Objects.isNull(getLoginUser()) ? null : (Objects.isNull(getLoginUser().getUser()) ? null : getLoginUser().getUser().getShop_id().intValue());
    }

    public static String getToken() {
        LoginUser loginUser = getLoginUser();
        return Objects.isNull(loginUser) ? null : loginUser.getAccess_token();
    }

    public static void remove() {
        user.remove();
    }
}
