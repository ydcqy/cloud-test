package com.ydcqy.ymq.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
//@Service
//@ConsumerListener
public class Cccc implements Bbbb {
    @Override
    public void xyz(String m, Integer n) {
        System.out.println("接受" + m + " " + n);
    }

}
