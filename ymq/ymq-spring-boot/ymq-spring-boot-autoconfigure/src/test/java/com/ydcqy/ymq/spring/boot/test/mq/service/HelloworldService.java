package com.ydcqy.ymq.spring.boot.test.mq.service;

import com.ydcqy.ymq.spring.annotation.Queue;

/**
 * @author xiaoyu
 */
@Queue(name = "notice.boot.hello.world")
public interface HelloworldService {
    void sayHi(String name);
}
