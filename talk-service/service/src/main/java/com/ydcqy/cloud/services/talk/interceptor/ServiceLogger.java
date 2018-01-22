package com.ydcqy.cloud.services.talk.interceptor;

import com.ydcqy.cloud.services.talk.exception.TalkException;
import com.ydcqy.cloud.services.talk.service.MeetService;
import com.ydcqy.cloud.services.talk.service.MeetServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by xiaoyu on 2018-01-22.
 */
@Slf4j
public class ServiceLogger implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("xxxxxxx");
        Object obj = invocation.proceed();
        log.info("yyyyyyy");
        return obj;
    }

    public static void main(String[] args) throws TalkException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new MeetServiceImpl());
        proxyFactory.addAdvice(new ServiceLogger());
        MeetService meetService = (MeetService) proxyFactory.getProxy();
        meetService.sayHello("张三", "李四");
    }
}
