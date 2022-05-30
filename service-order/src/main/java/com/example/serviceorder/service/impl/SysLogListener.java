package com.example.serviceorder.service.impl;

import com.example.common.service.log.SysLog;
import com.example.common.service.log.SysLogEvent;
import com.example.serviceorder.servicefeign.RemoteServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class SysLogListener {

    /**
     * @Autowired 按byType自动注入
     * @Resource 默认按 byName自动注入
     */
    @Resource
    RemoteServiceFeign remoteServiceFeign;

    @Async
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        log.info("请求记录日志: {}",sysLog);
        remoteServiceFeign.saveLog(sysLog);
    }

}