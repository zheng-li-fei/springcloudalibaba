package com.example.servicestock.service.impl;

import com.example.common.service.log.SysLog;
import com.example.common.service.log.SysLogEvent;
import com.example.servicestock.servicefeign.log.RemoteServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysLogListener {

    @Autowired
    RemoteServiceFeign remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        log.info("请求记录日志: {}",sysLog);
        remoteLogService.saveLog(sysLog);
    }

}