package com.ydcqy.ymq.spring.boot.test.consumer.service;

import com.ydcqy.ymq.spring.annotation.Queue;

/**
 * @author xiaoyu
 */
@Queue(name = "x.y.z")
public interface HelloworldService {
    void sayHi(String name);
}
