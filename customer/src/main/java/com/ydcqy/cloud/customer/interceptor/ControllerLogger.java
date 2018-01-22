package com.ydcqy.cloud.customer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2018/1/22.
 */
@Slf4j
@Aspect
@Component
public class ControllerLogger {
    @Around("controllerPoincut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around11111111111");
        Object result = pjp.proceed();
        log.info("around22222222222" + pjp.getTarget().getClass());
        return result;
    }

    @Pointcut("execution(public * com.ydcqy.cloud.customer.controller..*.*(..))")
    private void controllerPoincut() {
    }

}
