package com.ydcqy.ymq;

import com.ydcqy.ymq.activemq.ActiveMqConfiguration;
import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.activemq.ActiveMqMessage;
import com.ydcqy.ymq.activemq.ActiveMqProducer;
import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.producer.Producer;
import com.ydcqy.ymq.util.UnsafeUtil;
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
    private static final Unsafe unsafe = UnsafeUtil.getUnsafe();

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
//        final AtomicInteger n = new AtomicInteger();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        log.trace("哇卡卡卡");
//        int count = 10000;
//        CountDownLatch countDownLatch = new CountDownLatch(count);
//        long l = System.currentTimeMillis();
//        for (int i = 0; i < count; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        ActiveMqConfiguration cfg = new ActiveMqConfiguration();
//                        cfg.setUsername("张三");
//                        cfg.setPassword("123abc");
//                        producer.send(new ActiveMqQueue("com.test", ActiveMqQueue.Type.QUEUE), new ActiveMqMessage(cfg));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(n.incrementAndGet());
//                    countDownLatch.countDown();
//                }
//            });
//        }
//        countDownLatch.await();
//        System.out.println("时间 " + (System.currentTimeMillis() - l));


    }
}
