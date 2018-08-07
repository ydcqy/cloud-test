package com.ydcqy.ymq.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiaoyu
 */
@Slf4j
public class YmqNamespaceConsumerTest {
    public static void main(String[] args) throws Exception {
        new ClassPathXmlApplicationContext("applicationContextConsumer.xml");
        log.info("启动成功");
    }
}
