package com.zlf.commonbase.annotation.ignoretoken;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/1/5 9:22
 **/
@Getter
public enum IgnoreTokenTypeEnum {

    /**
     * visitor 游客
     */
    VISITOR("visitor","游客"),
    SHOP_REGISTER("login","商城注册"),
    SHOP_LOGIN("login","商城登录"),

    ;

    private String code;
    private String msg;

    IgnoreTokenTypeEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public static IgnoreTokenTypeEnum getIgnoreTokenTypeEnumByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for(IgnoreTokenTypeEnum ignoreTokenTypeEnum:IgnoreTokenTypeEnum.values()){
            if(ignoreTokenTypeEnum.getCode().equalsIgnoreCase(code)){
                return ignoreTokenTypeEnum;
            }
        }
        return null;
    }

}
