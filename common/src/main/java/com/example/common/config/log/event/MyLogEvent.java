package com.example.common.config.log.event;

import org.springframework.context.ApplicationEvent;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/5/16 10:56
 **/

public class MyLogEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyLogEvent(Object source) {
        super(source);
    }
}
