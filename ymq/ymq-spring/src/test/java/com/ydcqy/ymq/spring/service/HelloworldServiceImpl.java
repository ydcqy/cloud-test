package com.ydcqy.ymq.spring.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
public class HelloworldServiceImpl implements HelloworldService {
    @Override
    public void sayHi(String name) {
        log.info("接收到消息：{}", name);
    }
}
