package com.zlf.commonredis.config;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.zlf.commonbase.annotation.request.RequestLimitAnnotation;
import com.zlf.commonbase.annotation.request.RequestLimitTimeUnitEnum;
import com.zlf.commonbase.annotation.request.RequestLimitTypeEnum;
import com.zlf.commonbase.content.UserContext;
import com.zlf.commonbase.exception.BizException;
import com.zlf.commonredis.constants.RedisKeyConstant;
import com.zlf.commonredis.constants.RedissionConstantKey;
import com.zlf.commonredis.utils.RedissionLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @description: 基于自定义注解, 接口访问限制
 * @author: zhenglifei
 * @create: 2022/4/14 17:18
 **/
@Slf4j
@Aspect
@Service
public class RequestLimitAspect {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    HttpServletRequest request;

    @Value(value = "${spring.application.name}")
    String applicationName;

    /**
     * 1.定义切入点
     */
    @Pointcut(value = "@annotation(com.zlf.commonbase.annotation.request.RequestLimitAnnotation)")
    public void pointcut() {
    }

    /**
     * 2.环绕通知 ===> 正常情况执行在@Before和@After之前
     *
     * @param point
     * @return
     * @Before 之前的增强处理
     * @After 之后的增强处理
     */
    @Around(value = "pointcut()")
    public Object filterInterfaceRequest(ProceedingJoinPoint point) throws Throwable {
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
        RequestLimitAnnotation requestLimitAnnotation = method.getAnnotation(RequestLimitAnnotation.class);
        //1.3.1.1 获取注解中的限流类型
        RequestLimitTypeEnum requestLimitTypeEnum = requestLimitAnnotation.type();
        //1.3.1.2 获取注解中的最大请求访问次数
        int maxReqNum = requestLimitAnnotation.maxReqNum();
        //1.3.1.3 获取注解中的时间单位
        RequestLimitTimeUnitEnum timeUnitEnum = requestLimitAnnotation.unit();
        //1.3.1.4 获取注解中的备注
        String remake = requestLimitAnnotation.remake();


        //Redis锁    类名 + 接口方法名称
        String lockKey;
        //Redis键    类名 + 接口方法名称
        String redisKey;
        if (requestLimitTypeEnum.getType() == RequestLimitTypeEnum.INTERFACE.getType()) {
            //1.接口限流 (每秒最大访问接口次数)
            lockKey = String.format(RedissionConstantKey.OPEN_LOCK_INTERFACE_LIMIT_KEY, applicationName, className, methodName);
            redisKey = String.format(RedisKeyConstant.OPEN_INTERFACE_LIMIT_KEY_STR, applicationName, className, methodName);
            log.info("访问限流接口:接口访问限流:className-{},methodName-{}", className, method);
        } else if (requestLimitTypeEnum.getType() == RequestLimitTypeEnum.USER.getType()) {
            //2.用户限流 (每个用户,每秒最大访问接口次数)
            //获取用户唯一标识,读取redis中缓存的已经访问的次数
            String userId = UserContext.getUserId();
            lockKey = String.format(RedissionConstantKey.OPEN_LOCK_USER_LIMIT_KEY, applicationName, className, methodName, userId);
            redisKey = String.format(RedisKeyConstant.OPEN_USER_LIMIT_KEY_STR, applicationName, className, methodName, userId);
            log.info("访问限流接口:用户访问限流:className-{},methodName-{},userId-{}", className, method, userId);
        } else if (requestLimitTypeEnum.getType() == RequestLimitTypeEnum.IP.getType()) {
            //3.ip访问限流 （每个访问ip,每秒最大访问接口次数）
            //获取ip,读取redis中缓存的已经访问的次数 -- 需要网关配置获取请求来源的真实ip
            String remoteIp = ServletUtil.getClientIP(request);
            lockKey = String.format(RedissionConstantKey.OPEN_LOCK_IP_LIMIT_KEY, applicationName, className, methodName, remoteIp);
            redisKey = String.format(RedisKeyConstant.OPEN_IP_LIMIT_KEY_STR, applicationName, className, methodName, remoteIp);
            log.info("访问限流接口:ip访问限流:className-{},methodName-{},remoteIp-{}", className, method, remoteIp);
        } else {
            log.error("限流接口注解的访问类型错误! requestLimitAnnotation-{}", requestLimitAnnotation);
            throw new BizException("限流接口注解的访问类型错误");
        }

        RLock lock = RedissionLockUtil.lock(lockKey);
        try {
            boolean flag = limitFlowWindow(maxReqNum, timeUnitEnum.getTimeWindowLength(), redisKey);
            if (!flag) {
                //获取请求的 ip 信息等，
                log.error(remake + ",最大允许每" + timeUnitEnum.getDesc() + "访问" + maxReqNum + "次,访问限制!");
                throw new BizException("限制访问");
            }
        } finally {
            lock.unlock();
        }

        //2.方法执行******************
        proceed = point.proceed();

        //3.后置环绕通知开始执行******************
        return proceed;
    }


    /***********************************************限流算法*************************************************/
    /**
     * 1.基于滑动窗口的限流
     *
     * @param maxReqNum        单位时间最大访问次数
     * @param timeWindowLength 限流时间窗口(毫秒)
     * @param redisKey         限流使用的 rediskey
     * @return
     */
    private boolean limitFlowWindow(int maxReqNum, int timeWindowLength, String redisKey) {
        long currentTime = System.currentTimeMillis();
        if (redisTemplate.hasKey(redisKey)) {
            Integer count = redisTemplate.opsForZSet().rangeByScore(redisKey, currentTime - timeWindowLength, currentTime).size();
            //删除前面的缓存数据
            redisTemplate.opsForZSet().removeRangeByScore(redisKey, 0, currentTime - timeWindowLength);
            log.debug(redisKey + " 访问数量: {}", count);
            if (count >= maxReqNum) {
                return false;
            }
        }
        redisTemplate.opsForZSet().add(redisKey, IdUtil.fastSimpleUUID(), currentTime);
        return true;
    }

}
