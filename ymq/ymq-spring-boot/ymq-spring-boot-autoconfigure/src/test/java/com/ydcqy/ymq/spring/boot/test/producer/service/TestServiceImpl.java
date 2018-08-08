package com.ydcqy.ymq.spring.boot.test.producer.service;

import com.ydcqy.ymq.spring.annotation.Producer;
import com.ydcqy.ymq.spring.boot.test.producer.service.mq.HelloworldService;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
@Service
public class TestServiceImpl {
//    @Producer
    private HelloworldService helloworldService;

    public void test() {
        helloworldService.sayHi("张三");
    }
}
