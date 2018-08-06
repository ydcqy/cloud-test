package com.ydcqy.ymq.spring;

import com.alibaba.fastjson.JSON;
import com.ydcqy.ymq.spring.support.MessageWrapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author xiaoyu
 */
public class ProducerBean implements FactoryBean, InitializingBean, DisposableBean, BeanClassLoaderAware {
    private QueueBean   queueRef;
    private ConfigBean  configBean;
    private ClassLoader classLoader;

    public ProducerBean() {
    }

    public ConfigBean getConfigBean() {
        return configBean;
    }

    public void setConfigBean(ConfigBean configBean) {
        this.configBean = configBean;
    }

    public QueueBean getQueueRef() {
        return queueRef;
    }

    public void setQueueRef(QueueBean queueRef) {
        this.queueRef = queueRef;

    }

    public void destroy() throws Exception {

    }

    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader, new Class[]{queueRef.getInterface()}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                queueRef.getInterface()
                if (ClassUtils.hasMethod(queueRef.getInterface(), method.getName(), method.getParameterTypes())) {
                    System.out.println(JSON.toJSONString(args));
                    MessageWrapper wrapper = new MessageWrapper();
                    wrapper.setMethodName(method.getName());
                    wrapper.setInterfaceName(queueRef.getInterface().getName());
                    wrapper.setParamsList(Arrays.asList(args));
                    return null;
                }
                return null;
            }
        });
    }

    public Class<?> getObjectType() {
        if (queueRef == null) {
            return null;
        }
        return queueRef.getInterface();
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

}
