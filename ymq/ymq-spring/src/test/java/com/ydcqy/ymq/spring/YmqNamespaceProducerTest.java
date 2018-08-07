package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.spring.service.HelloworldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
@Slf4j
public class YmqNamespaceProducerTest {
    public static void main(String[] args) throws Exception {
        final ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContextProducer.xml");
        final HelloworldService helloworldService = ac.getBean(HelloworldService.class);
        log.info("启动成功");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int count = 50000;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        long l = System.currentTimeMillis();
        final AtomicInteger n = new AtomicInteger(1);
        for (int i = 0; i < count; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long ss = System.currentTimeMillis();
                    helloworldService.sayHi("张三");
                    System.out.println(n.getAndIncrement() + "，耗时：" + (System.currentTimeMillis() - ss));
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("时间 " + (System.currentTimeMillis() - l));
    }
}
