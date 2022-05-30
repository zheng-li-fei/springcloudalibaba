package com.example.common.config.log;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {

    /**
     * 描述
     * @return
     */
    String value();

}
