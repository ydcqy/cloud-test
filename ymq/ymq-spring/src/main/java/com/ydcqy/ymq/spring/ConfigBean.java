package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.spring.util.ConsumerHolder;
import com.ydcqy.ymq.spring.util.ProducerHolder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author xiaoyu
 */
public class ConfigBean implements InitializingBean, ResourceLoaderAware, ApplicationListener<ContextRefreshedEvent> {
    public static final String CONFIG_BEAN_ID = "configBean";
    private String         path;
    private Configuration  configuration;
    private String         active;
    private ResourceLoader resourceLoader;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void afterPropertiesSet() throws Exception {
        Resource resource = resourceLoader.getResource(path);
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ProducerHolder.get(this);
            ConsumerHolder.listen();
        } catch (MqException e) {
            throw new RuntimeException(e);
        }
    }
}
