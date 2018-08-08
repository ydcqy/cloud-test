package com.ydcqy.ymq.spring.service;

import com.ydcqy.ymq.spring.support.EnableYmq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
@Slf4j
@Service
@EnableYmq
public class HelloworldServiceImpl implements HelloworldService {
    @Override
    public void sayHi(String name) {
        log.info("接收到消息：{}", name);
    }
}
