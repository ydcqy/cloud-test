package com.ydcqy.ymq.spring.boot.test.producer.service;

import com.ydcqy.ymq.spring.annotation.Producer;
import com.ydcqy.ymq.spring.boot.test.mq.service.HelloworldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
@Slf4j
@Service
public class TestServiceImpl {
    @Producer
    private HelloworldService helloworldService;

    public void test(int i) {
        helloworldService.sayHi("张三" + i);
//        log.info("发送消息成功");
    }
}
