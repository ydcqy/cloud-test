package com.ydcqy.cloud.services.top.support;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FreemarkerConfiguration extends Configuration {
    public FreemarkerConfiguration() {
        setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "templates");
        setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        setClassicCompatible(true);
        setDefaultEncoding("UTF-8");
        setLocale(new Locale("zh_CN"));
    }
}
