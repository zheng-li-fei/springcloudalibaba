package com.example.servicestock.servicefeign.log;

import com.example.common.config.log.event.SysLog;
import com.example.servicestock.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/5/16 11:32
 **/
@FeignClient(value = "gateway", fallback = RemoteLogServiceFeignFallback.class,configuration = OpenFeignConfig.class)
public interface RemoteLogServiceFeign {

    /**
     * 保存日志
     *
     * @param sysLog 日志实体
     * @return succes、false
     */
    @PostMapping(value = "/service-log/saveLog")
    Boolean saveLog(@RequestBody SysLog sysLog);

}
