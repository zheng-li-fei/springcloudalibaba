package com.example.common.config.log.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/5/16 10:59
 **/
@Slf4j
@Component
public class MyLogAnnotationListener implements ApplicationListener<MyLogEvent> {

    @Override
    public void onApplicationEvent(MyLogEvent event) {
        log.info("注解监听器:" + event);
    }
}
