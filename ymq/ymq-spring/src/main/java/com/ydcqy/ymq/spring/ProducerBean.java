package com.ydcqy.ymq.spring;

import com.alibaba.fastjson.JSON;
import com.ydcqy.ymq.activemq.ActiveMqMessage;
import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.kafka.KafkaMessage;
import com.ydcqy.ymq.kafka.KafkaQueue;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.Queue;
import com.ydcqy.ymq.rabbitmq.RabbitMqMessage;
import com.ydcqy.ymq.rabbitmq.RabbitMqQueue;
import com.ydcqy.ymq.spring.support.MessageWrapper;
import com.ydcqy.ymq.spring.util.ActiveType;
import com.ydcqy.ymq.spring.util.ProducerHolder;
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
                if (ClassUtils.hasMethod(queueRef.getInterface(), method.getName(), method.getParameterTypes())) {
                    MessageWrapper wrapper = new MessageWrapper();
                    wrapper.setMethodName(method.getName());
                    wrapper.setInterfaceName(queueRef.getInterface().getName());
                    wrapper.setParamsList(Arrays.asList(args));
                    String active = configBean.getActive();
                    Queue queue = null;
                    Message message = null;
                    switch (active) {
                        case ActiveType.ACTIVEMQ:
                            queue = new ActiveMqQueue(queueRef.getName(), queueRef.getQueueType());
                            message = new ActiveMqMessage(wrapper);
                            break;
                        case ActiveType.RABBITMQ:
                            queue = new RabbitMqQueue(queueRef.getName(), queueRef.getQueueType());
                            message = new RabbitMqMessage(wrapper);
                            break;
                        case ActiveType.KAFKA:
                            queue = new KafkaQueue(queueRef.getName(), queueRef.getQueueType());
                            message = new KafkaMessage(wrapper);
                            break;
                    }
                    ProducerHolder.get(configBean).send(queue, message);
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
        ProducerHolder.get(configBean);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

}
