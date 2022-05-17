package com.example.common.config.log.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/5/16 10:54
 **/
@Slf4j
@Configuration
public class LogEventConfig {

    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        log.info("事件触发:" + event.getClass().getName());
    }

}
