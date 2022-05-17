package com.example.serviceorder.service.impl;

import com.example.common.config.log.event.SysLog;
import com.example.common.config.log.event.SysLogEvent;
import com.example.serviceorder.servicefeign.ServiceFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

    //日志处理业务类  自定义 syslog  日志类
    private final ServiceFeign serviceFeign;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        serviceFeign.saveLog(sysLog);
    }

}