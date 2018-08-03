package com.ydcqy.ymq.spring.config;

import com.ydcqy.ymq.spring.ConfigFileBean;
import com.ydcqy.ymq.spring.ConsumerListenerBean;
import com.ydcqy.ymq.spring.ProducerBean;
import com.ydcqy.ymq.spring.QueueBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author xiaoyu
 */
public class YmqNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("config-file", new YmqBeanDefinitionParser(ConfigFileBean.class, true));
        registerBeanDefinitionParser("queue", new YmqBeanDefinitionParser(QueueBean.class, false));
        registerBeanDefinitionParser("consumer-listener", new YmqBeanDefinitionParser(ConsumerListenerBean.class, false));
        registerBeanDefinitionParser("producer", new YmqBeanDefinitionParser(ProducerBean.class, false));
    }
}
