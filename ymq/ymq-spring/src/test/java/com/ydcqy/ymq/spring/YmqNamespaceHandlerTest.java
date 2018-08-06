package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.spring.service.AbcService;
import com.ydcqy.ymq.spring.service.Bbbb;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiaoyu
 */
public class YmqNamespaceHandlerTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        while (true) {
            Thread.sleep(10);
            ac.getBean(Bbbb.class).xyz("你好", 123);
            System.out.println("发送消息");
        }

//        ac.getBean(Ccc.class).aaa("滚", 123);
//        Object mqAbcService = ac.getBean(Bbbb.class);
//        System.out.println(mqAbcService);

    }
}
