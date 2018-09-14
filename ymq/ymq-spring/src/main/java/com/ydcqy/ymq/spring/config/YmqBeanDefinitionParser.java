package com.ydcqy.ymq.spring.config;

import com.ydcqy.ymq.spring.ConfigBean;
import com.ydcqy.ymq.spring.ConsumerListenerBean;
import com.ydcqy.ymq.spring.ProducerBean;
import com.ydcqy.ymq.spring.QueueBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author xiaoyu
 */
public class YmqBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;


    public YmqBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;

    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        String id = element.getAttribute("id");
        if (ConfigBean.class == beanClass) {
            String path = element.getAttribute("path");
            beanDefinition.getPropertyValues().add("path", path);
            String active = element.getAttribute("active");
            beanDefinition.getPropertyValues().add("active", active);
            id = "configBean";
        } else if (QueueBean.class == beanClass) {
            if (StringUtils.isEmpty(id)) {
                throw new IllegalStateException("The queue id be defined");
            }
            String interfaceName = element.getAttribute("interface");
            try {
                Class<?> interfaceClass = ClassUtils.forName(interfaceName, getClass().getClassLoader());
                beanDefinition.getPropertyValues().add("interface", interfaceClass);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
            String name = element.getAttribute("name");
            if (StringUtils.isEmpty(name)) {
                name = interfaceName;
            }
            beanDefinition.getPropertyValues().add("name", name);
            String type = element.getAttribute("type");
            beanDefinition.getPropertyValues().add("type", type);
        } else if (ConsumerListenerBean.class == beanClass) {
            String queueRef = element.getAttribute("queue-ref");
            String implementRef = element.getAttribute("implement-ref");
            beanDefinition.getPropertyValues().add("queueRef", new RuntimeBeanReference(queueRef));
            beanDefinition.getPropertyValues().add("implementRef", new RuntimeBeanReference(implementRef));
            beanDefinition.getPropertyValues().add("configBean", new RuntimeBeanReference(ConfigBean.CONFIG_BEAN_ID));
            if (StringUtils.isEmpty(id)) {
                id = "consumerListener";
                int counter = 2;
                while (parserContext.getRegistry().containsBeanDefinition(id)) {
                    id = "consumerListener" + (counter++);
                }
            }
        } else if (ProducerBean.class == beanClass) {
            if (StringUtils.isEmpty(id)) {
                throw new IllegalStateException("The producer id be defined");
            }
            String queueRef = element.getAttribute("queue-ref");
            beanDefinition.getPropertyValues().add("queueRef", new RuntimeBeanReference(queueRef));
            beanDefinition.getPropertyValues().add("configBean", new RuntimeBeanReference(ConfigBean.CONFIG_BEAN_ID));
        }
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        return beanDefinition;
    }
}
