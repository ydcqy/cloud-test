package com.ydcqy.ymq.spring.service;

import com.ydcqy.ymq.spring.annotation.Producer;
import org.apache.camel.Produce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiaoyu
 */
@Service
public class AbcServiceImpl implements AbcService {
    @Producer
    private Bbbb bb;

    public AbcServiceImpl( ) {
        System.out.println("构造AbcServiceImpl");
    }

    @Override
    public void abc() {
        bb.xyz("哈323哈", 11233);
    }
}
