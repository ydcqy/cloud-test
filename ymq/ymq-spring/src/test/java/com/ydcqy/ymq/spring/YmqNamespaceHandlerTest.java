package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.spring.service.AbcService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiaoyu
 */
public class YmqNamespaceHandlerTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(ac.getBean(AbcService.class));
        System.out.println(ac.getBean("mqAbcService"));
        System.out.println(ac.getBean("mqAbcService"));



    }
}
