package com.ydcqy.ymq;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Abc abc = new Abc();

        for (int i = 0; i < 10000000; i++) {
            executorService.execute(abc);
//            System.out.println(i);
        }
        System.out.println("666666666");

    }

    static class Abc implements Runnable {
        AtomicInteger n = new AtomicInteger(1);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        @Override
        public void run() {
            try {
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            log.info("哈喽,{}", n.getAndIncrement());
        }
    }
}

