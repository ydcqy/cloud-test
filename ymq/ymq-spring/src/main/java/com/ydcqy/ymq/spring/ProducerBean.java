package com.ydcqy.ymq.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author xiaoyu
 */
public class ProducerBean implements FactoryBean, InitializingBean, DisposableBean {

    public void destroy() throws Exception {

    }

    public Object getObject() throws Exception {
        return null;
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }

    public void afterPropertiesSet() throws Exception {

    }
}
