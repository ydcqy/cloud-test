package com.ydcqy.cloud.services.talk.interceptor;

import com.ydcqy.cloud.services.talk.service.MeetService;
import com.ydcqy.cloud.services.talk.service.MeetServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.StreamUtils;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiaoyu on 2018-01-22.
 */
@Slf4j
public class ServiceLogger implements MethodInterceptor, Serializable {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("xxxxxxx");
        Object obj = invocation.proceed();
        log.info("yyyyyyy");
        return obj;
    }

    public static void main(String[] args) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new MeetServiceImpl());
        proxyFactory.addAdvice(new ServiceLogger());
        proxyFactory.setProxyTargetClass(false);
        MeetServiceImpl meetService = (MeetServiceImpl) proxyFactory.getProxy();
//        meetService.sayHello("张三", "李四");
        System.out.println(AopUtils.isJdkDynamicProxy(meetService));

        byte[] acabcs = ProxyGenerator.generateProxyClass("MeetServiceImplProxy", MeetServiceImpl.class.getInterfaces());
        String path = MeetServiceImpl.class.getResource(".").getPath();
        System.out.println(path);
        StreamUtils.copy(acabcs, new FileOutputStream(path + "MeetServiceImplProxy.class"));
        MeetService o = (MeetService) Proxy.newProxyInstance(MeetServiceImpl.class.getClassLoader(), MeetServiceImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        System.out.println(o instanceof MeetService);
    }

}
