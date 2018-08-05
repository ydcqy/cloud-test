package com.ydcqy.ymq.spring;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author xiaoyu
 */
public class ProducerBean implements FactoryBean, InitializingBean, DisposableBean, BeanClassLoaderAware {
    private QueueBean   queueRef;
    private ConfigBean  configBean;
    private ClassLoader classLoader;

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
        System.out.println("ddddddddddd");
        return "abc";
    }

    public Class<?> getObjectType() {
        return null;
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
