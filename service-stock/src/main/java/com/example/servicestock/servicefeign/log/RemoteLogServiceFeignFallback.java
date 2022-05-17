package com.example.servicestock.servicefeign.log;

import com.example.common.config.log.event.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteLogServiceFeignFallback implements RemoteLogServiceFeign {

    @Override
    public Boolean saveLog(SysLog sysLog) {
        log.error("日志服务降级了,sysLog {}", sysLog);
        return false;
    }
}