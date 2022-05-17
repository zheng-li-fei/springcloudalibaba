package com.example.common.service.log;

import com.example.common.config.log.event.MyLogEvent;
import com.example.common.config.log.event.SysLog;
import com.example.common.config.log.annotation.SysLogAnnotation;
import com.example.common.enums.LogTypeEnum;
import com.example.common.utils.SysLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/5/16 13:46
 **/
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    SysLogPublisher sysLogPublisher;

    @Pointcut(value = "@annotation(com.example.common.config.log.annotation.SysLogAnnotation)")
    public void Pointcut() {
    }

    @Around(value = "Pointcut()")
    public Object SysLogAspect(ProceedingJoinPoint point) throws Throwable {
        Object proceed = null;
        //1.前置环绕通知开始执行******************

        //1.1 获取目标对象类
        Class<?> targetCls = point.getTarget().getClass();
        //获取类名称
        String className = targetCls.getSimpleName();

        //1.2 获取方法签名信息
        MethodSignature ms = (MethodSignature) point.getSignature();
        //1.3 获取方法对象
        Method method = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        //方法名称
        String methodName = method.getName();

        //1.3.1 获取方法上的注解对象
        SysLogAnnotation logAnnotation = method.getAnnotation(SysLogAnnotation.class);

        SysLog logVo = SysLogUtils.getSysLog();
        logVo.setTitle(logAnnotation.value());

        //2.方法执行******************
        Long startTime = System.currentTimeMillis();
        try {
            proceed = point.proceed();
        } catch (Exception e) {
            logVo.setType(LogTypeEnum.ERROR.getType());
            logVo.setException(e.getMessage());
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            logVo.setTime(endTime - startTime);
            //发送异步日志事件
            sysLogPublisher.publishEvent(new MyLogEvent(logVo));
        }

        //3.后置环绕通知开始执行******************
        return proceed;
    }

}
