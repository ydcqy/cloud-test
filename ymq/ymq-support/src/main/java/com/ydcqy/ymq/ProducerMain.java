package com.ydcqy.ymq;

import com.ydcqy.ymq.producer.Producer;
import com.ydcqy.ymq.rabbitmq.RabbitMqConfigurationFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConnectionFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqMessage;
import com.ydcqy.ymq.rabbitmq.RabbitMqProducer;
import com.ydcqy.ymq.rabbitmq.RabbitMqQueue;
import com.ydcqy.ymq.util.UnsafeUtils;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
@Slf4j
public class ProducerMain {
    private static final Unsafe unsafe = UnsafeUtils.getUnsafe();

    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private volatile Object value = new Object();

    public static void main(String[] args) throws Exception {
//        final Producer producer = new ActiveMqProducer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration()));
        final Producer producer = new RabbitMqProducer(new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration()));
        final AtomicInteger n = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        log.trace("哇卡卡卡");
        int count = 100000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long ss = 0;
                    try {
                        ss = System.currentTimeMillis();

//                        producer.send(new ActiveMqQueue("x.y.z", ActiveMqQueue.Type.QUEUE), new RabbitMqMessage("哈喽大圣归来"));
                        producer.send(new RabbitMqQueue("x.y.z"), new RabbitMqMessage("哈喽大圣归来"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(n.incrementAndGet() + "，耗时：" + (System.currentTimeMillis() - ss));
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("时间 " + (System.currentTimeMillis() - l));


    }
}
