package com.ydcqy.ymq;

import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.activemq.ActiveMqMessage;
import com.ydcqy.ymq.activemq.ActiveMqProducer;
import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.kafka.KafkaConfigurationFactory;
import com.ydcqy.ymq.kafka.KafkaMessage;
import com.ydcqy.ymq.kafka.KafkaProducer;
import com.ydcqy.ymq.kafka.KafkaQueue;
import com.ydcqy.ymq.message.QueueType;
import com.ydcqy.ymq.producer.Producer;
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
        final Producer producer = new ActiveMqProducer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration()));
//        final Producer producer = new RabbitMqProducer(new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration()));
//        final Producer producer = new KafkaProducer(new KafkaConfigurationFactory().getConfiguration());
        final AtomicInteger n = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        log.trace("哇卡卡卡");
        int count = 50000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long ss = 0;
                    try {
                        ss = System.currentTimeMillis();
                        System.out.println("ddddd");
                        producer.send(new ActiveMqQueue("x.y.z", QueueType.POINT_TO_POINT), new ActiveMqMessage("哈喽大圣归来"));
//                        producer.send(new KafkaQueue("x.y.z11113", QueueType.POINT_TO_POINT), new KafkaMessage("哈喽大圣归来"));

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
