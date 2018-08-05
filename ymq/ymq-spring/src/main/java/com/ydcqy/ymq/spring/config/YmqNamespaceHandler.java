package com.ydcqy.ymq.spring.config;

import com.ydcqy.ymq.spring.ConfigBean;
import com.ydcqy.ymq.spring.ConsumerListenerBean;
import com.ydcqy.ymq.spring.ProducerBean;
import com.ydcqy.ymq.spring.QueueBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author xiaoyu
 */
public class YmqNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("config", new YmqBeanDefinitionParser(ConfigBean.class ));
        registerBeanDefinitionParser("queue", new YmqBeanDefinitionParser(QueueBean.class ));
        registerBeanDefinitionParser("consumer-listener", new YmqBeanDefinitionParser(ConsumerListenerBean.class ));
        registerBeanDefinitionParser("producer", new YmqBeanDefinitionParser(ProducerBean.class ));
    }
}
