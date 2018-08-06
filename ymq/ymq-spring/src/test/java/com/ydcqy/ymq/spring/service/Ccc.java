package com.ydcqy.ymq.spring.service;

import com.ydcqy.ymq.spring.annotation.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
@Service
public class Ccc {
    @Producer
    private Bbbb bbbb;

    public void aaa(String a, Integer b) {
        bbbb.xyz(a, b);
    }

}
