package com.example.common.service.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @description: 发布事件
 * @author: zhenglifei
 * @create: 2022/5/16 14:18
 **/
@Slf4j
@Component
public class SysLogPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发布事件
     */
    public void publishEvent(ApplicationEvent event){
        applicationEventPublisher.publishEvent(event);
    }
}
