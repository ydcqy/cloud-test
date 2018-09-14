package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.spring.util.ConsumerHolder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author xiaoyu
 */
public class ConfigBean implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {
    public static final String CONFIG_BEAN_ID = "configBean";
    private String        path;
    private Configuration configuration;
    private String        active;

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

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ConsumerHolder.listen();
        } catch (MqException e) {
            throw new RuntimeException(e);
        }
    }
}
