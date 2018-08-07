package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.consumer.Consumer;
import com.ydcqy.ymq.kafka.KafkaQueue;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.MessageListener;
import com.ydcqy.ymq.message.Queue;
import com.ydcqy.ymq.rabbitmq.RabbitMqQueue;
import com.ydcqy.ymq.spring.support.MessageWrapper;
import com.ydcqy.ymq.spring.util.ActiveType;
import com.ydcqy.ymq.spring.util.ConsumerHolder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyu
 */
public class ConsumerListenerBean implements InitializingBean, DisposableBean, ResourceLoaderAware {


    private QueueBean      queueRef;
    private Object         implementRef;
    private ConfigBean     configBean;
    private ResourceLoader resourceLoader;

    public QueueBean getQueueRef() {
        return queueRef;
    }

    public void setQueueRef(QueueBean queueRef) {
        this.queueRef = queueRef;
    }

    public Object getImplementRef() {
        return implementRef;
    }

    public void setImplementRef(Object implementRef) {
        this.implementRef = implementRef;
    }

    public ConfigBean getConfigBean() {
        return configBean;
    }

    public void setConfigBean(ConfigBean configBean) {
        this.configBean = configBean;
    }

    public void setBeanName(String s) {

    }

    public void destroy() throws Exception {

    }

    public void afterPropertiesSet() throws Exception {
        String active = configBean.getActive();
        Consumer consumer = ConsumerHolder.get(configBean);
        Queue queue = null;
        switch (active) {
            case ActiveType.ACTIVEMQ:
                queue = new ActiveMqQueue(queueRef.getName(), queueRef.getQueueType());
                break;
            case ActiveType.RABBITMQ:
                queue = new RabbitMqQueue(queueRef.getName(), queueRef.getQueueType());
                break;
            case ActiveType.KAFKA:
                queue = new KafkaQueue(queueRef.getName(), queueRef.getQueueType());
                break;
        }
        bindListener(consumer, queue, queueRef.getInterface());
    }

    private void bindListener(Consumer consumer, Queue queue, final Class<?> interfaceClass) {
        consumer.bindMessageListener(queue, new MessageListener() {
            @Override
            public void onMessage(Message message) {
                MessageWrapper wrapper = message.getDecodeObject(MessageWrapper.class);
                String interfaceName = wrapper.getInterfaceName();
                if (!interfaceClass.getName().equals(interfaceName)) {
                    throw new ClassCastException(interfaceName);
                }
                List<Object> paramsList = wrapper.getParamsList();
                List<Class<?>> classes = new ArrayList<>();
                for (Object o : paramsList) {
                    classes.add(o.getClass());
                }
                try {
                    Method method = interfaceClass.getMethod(wrapper.getMethodName(), classes.toArray(new Class<?>[0]));
                    method.invoke(implementRef, paramsList.toArray());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


}
