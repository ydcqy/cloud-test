package com.ydcqy.cloud.personal.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lenovo on 2018/1/23.
 */
@Slf4j
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/js/**", "/img/**", "/video/**", "/css/**", "/font-awesome-4.5.0/**")
//                .addResourceLocations("classpath:/WEB-INF/js/", "classpath:/WEB-INF/img/", "classpath:/WEB-INF/video/", "classpath:/WEB-INF/css/", "classpath:/WEB-INF/font-awesome-4.5.0/")
//        ;
    }
}
