package com.ydcqy.ymq.spring.service;

import com.ydcqy.ymq.spring.annotation.Producer;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
@Service
public class AbcServiceImpl implements AbcService {
    @Producer
    private Bbbb bb;


    @Override
    public void abc() {
        bb.xyz("哈323哈", 11233);
        System.out.println(bb.toString());
    }
}
