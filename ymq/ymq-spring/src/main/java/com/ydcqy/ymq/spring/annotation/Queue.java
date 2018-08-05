package com.ydcqy.ymq.spring.annotation;

import com.ydcqy.ymq.message.QueueType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiaoyu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Queue {
    String name();

    QueueType type() default QueueType.POINT_TO_POINT;
}
