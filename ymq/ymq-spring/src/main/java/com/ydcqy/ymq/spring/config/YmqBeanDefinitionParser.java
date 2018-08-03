package com.ydcqy.ymq.spring.config;

import com.ydcqy.ymq.spring.ConfigFileBean;
import com.ydcqy.ymq.spring.QueueBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author xiaoyu
 */
public class YmqBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;
    private final boolean  required;

    public YmqBeanDefinitionParser(Class<?> beanClass, boolean required) {
        this.beanClass = beanClass;
        this.required = required;
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        if (ConfigFileBean.class == beanClass) {
            String path = element.getAttribute("path");
            beanDefinition.getPropertyValues().add("path", path);
            parserContext.getRegistry().registerBeanDefinition(StringUtils.uncapitalize(beanClass.getSimpleName()), beanDefinition);
        } else if (QueueBean.class == beanClass) {

        }


        return beanDefinition;
    }
}
