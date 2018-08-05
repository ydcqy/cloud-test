package com.ydcqy.ymq.spring.service;

import com.ydcqy.ymq.spring.annotation.Queue;

/**
 * @author xiaoyu
 */
@Queue(name = "x.y.z")
public interface Bbbb {
    void xyz(String m, Integer n);
}
