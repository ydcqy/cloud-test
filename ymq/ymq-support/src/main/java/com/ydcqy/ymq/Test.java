package com.ydcqy.ymq;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (;;)
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                log.info("dgdsg");
//            }
//        });
    }
}
