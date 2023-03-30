package com.zlf.commonbase.annotation.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 限流接口注解
 * @author: zhenglifei
 * @create: 2021/8/18 16:00
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface RequestLimitAnnotation {

    /**
     * 限流类型：
     * 1.接口访问限流
     * 2.用户访问限流
     * 3.ip访问限流
     */
    RequestLimitTypeEnum type();

    /**
     * 单位时间最大请求访问次数
     * type:
     * 1.接口访问限流    -每秒最大访问接口次数
     * 2.每个用户       -用户每秒最大访问接口次数
     * 3.每个访问ip     -ip每秒最大访问接口次数
     */
    int maxReqNum();

    /**
     * 限流时间单位
     */
    RequestLimitTimeUnitEnum unit();

    /**
     * 备注
     */
    String remake() default "";

}
