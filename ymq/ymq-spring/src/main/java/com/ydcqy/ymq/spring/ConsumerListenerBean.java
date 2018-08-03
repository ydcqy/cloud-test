package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.message.Queue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author xiaoyu
 */
public class ConsumerListenerBean implements InitializingBean, DisposableBean, ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>, BeanNameAware {
    private Queue queue;


    public void setBeanName(String s) {

    }

    public void destroy() throws Exception {

    }

    public void afterPropertiesSet() throws Exception {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
