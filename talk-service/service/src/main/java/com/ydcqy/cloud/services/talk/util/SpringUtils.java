package com.ydcqy.cloud.services.talk.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by xiaoyu on 2017/10/31.
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static volatile ApplicationContext ac = null;

    private SpringUtils() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == ac) {
            ac = applicationContext;
        }
    }

    public static ApplicationContext getCurrentApplicationContext() {
        return ac;
    }

    public static <T> T getBean(Class<T> cls) {
        return ac.getBean(cls);
    }
}
