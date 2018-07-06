package com.ydcqy.cloud.services.talk;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

import java.io.InputStream;
import java.net.URL;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws Exception {
//        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//        System.out.println(Arrays.asList(resourceResolver.getResources("mapper/*")).get(0).getURL());
//        \org\springframework\boot\logging\logback\defaults.xml
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("/");

        System.out.println( ClassLoader.getSystemResource("org/springframework/boot/logging/logback/defaults.xml"));
        System.out.println(Thread.currentThread().getContextClassLoader());

    }

}
