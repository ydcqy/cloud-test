package com.ydcqy.ymq.spring;

import com.alibaba.fastjson.JSON;
import com.ydcqy.ymq.spring.annotation.Producer;
import com.ydcqy.ymq.spring.service.AbcService;
import com.ydcqy.ymq.spring.service.Bbbb;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
public class YmqNamespaceHandlerTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AbcService bean = ac.getBean(AbcService.class);
        bean.abc();
        System.out.println(JSON.toJSONString(ac.getBean(Bbbb.class)));

    }
}
