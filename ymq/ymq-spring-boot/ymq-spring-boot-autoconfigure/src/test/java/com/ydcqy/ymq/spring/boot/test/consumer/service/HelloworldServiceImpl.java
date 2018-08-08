package com.ydcqy.ymq.spring.boot.test.consumer.service;

import com.ydcqy.ymq.spring.annotation.ConsumerListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
@Slf4j
@Service
@ConsumerListener
public class HelloworldServiceImpl implements HelloworldService {
    @Override
    public void sayHi(String name) {
        log.info("接收到消息：{}", name);
    }
}
