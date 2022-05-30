package com.example.common.service.log;

import org.springframework.context.ApplicationEvent;

public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog source) {
        super(source);
    }

}