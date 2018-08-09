package com.ydcqy.ymq.spring.boot.test.producer;

import com.ydcqy.ymq.spring.boot.test.producer.service.TestServiceImpl;
import com.ydcqy.ymq.spring.support.EnableYmq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
@Slf4j
@EnableYmq("com.ydcqy")
@SpringBootApplication
public class YmqProducerApplicationTest implements ApplicationContextAware {
    private static ApplicationContext ac;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(YmqProducerApplicationTest.class, args);
        log.info("==========Spring Boot YmqApplicationTest启动成功！==========");
        TestServiceImpl testService = ac.getBean(TestServiceImpl.class);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int count = 50000;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        long l = System.currentTimeMillis();
        final AtomicInteger n = new AtomicInteger(1);
        for (int i = 0; i < count; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long ss = System.currentTimeMillis();
                    testService.test(finalI);
                    System.out.println(n.getAndIncrement() + "，耗时：" + (System.currentTimeMillis() - ss));
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("时间 " + (System.currentTimeMillis() - l));
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ac = ac;
    }
}
