package com.example.common.config.log.event;

import org.springframework.context.ApplicationEvent;

public class SysLogEvent extends ApplicationEvent {
 
	public SysLogEvent(SysLog source) {
		super(source);
	}
 
}