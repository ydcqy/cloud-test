package com.ydcqy.kiter;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by lenovo on 2018/1/26.
 */
public class Test {
    public int a = 1111;
    public int b = 1111;

    public void abc() {
        a = 2222;

        b = 2222;
    }

    public static void main(String[] args) throws InterruptedException {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getClass());
        }

        CyclicBarrier cyclicBarrier = new CyclicBarrier(100000, new Runnable() {
            @Override
            public void run() {
                System.out.println("完成");
            }
        });
        for (int i = 0; i < 100000; i++) {
            Test test = new Test();
            new Thread() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1111");
                }
            }.start();
        }
    }

}