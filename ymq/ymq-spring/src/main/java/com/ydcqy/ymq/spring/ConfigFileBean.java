package com.ydcqy.ymq.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author xiaoyu
 */
public class ConfigFileBean implements InitializingBean, ResourceLoaderAware {
    private String         path;
    private ResourceLoader resourceLoader;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void afterPropertiesSet() throws Exception {
        Resource resource = resourceLoader.getResource(path);
        System.out.println(resourceLoader.getClass());
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        System.out.println("bbbb");
    }
}
