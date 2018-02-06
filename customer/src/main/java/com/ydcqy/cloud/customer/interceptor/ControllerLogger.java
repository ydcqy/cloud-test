package com.ydcqy.cloud.customer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xiaoyu
 */
@Slf4j
@Aspect
@Component
public class ControllerLogger {
    @Around("controllerPoincut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Class withinType = pjp.getSourceLocation().getWithinType();

        log.info("进入Logger\t{} ", withinType.getName());
        Object result = pjp.proceed();
        return result;
    }

    @Pointcut("execution(public * com.ydcqy.cloud.customer.controller..*.*(..))")
    private void controllerPoincut() {
    }

}
