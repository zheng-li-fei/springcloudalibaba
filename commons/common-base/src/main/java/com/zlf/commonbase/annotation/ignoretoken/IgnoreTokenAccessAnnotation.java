package com.zlf.commonbase.annotation.ignoretoken;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 接口方法注解 - 不需要token即可访问的接口,拦截器中不拦截此注解的接口
 * @author: zhenglifei
 * @create: 2022/1/5 9:15
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IgnoreTokenAccessAnnotation {

    /**
     * 类型
     */
    IgnoreTokenTypeEnum type() default IgnoreTokenTypeEnum.VISITOR;

    /**
     * 备注
     */
    String remark() default "";

}
