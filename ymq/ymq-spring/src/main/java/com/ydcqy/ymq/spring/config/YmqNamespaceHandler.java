package com.ydcqy.ymq.spring.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author xiaoyu
 */
public class YmqNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        System.out.println("活动回顾");
        registerBeanDefinitionParser("consumer", new ConsumerBeanDefinitionParser());
    }
}
