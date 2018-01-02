package com.ydcqy.cloud.customer.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by xiaoyu on 2017/11/27.
 */
@Aspect
public class
LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("log..............");
        return null;
    }


}
