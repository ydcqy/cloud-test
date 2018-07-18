package com.ydcqy.ymq;

import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConfiguration;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.activemq.ActiveMqMessage;
import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.activemq.ActiveMqProducer;
import com.ydcqy.ymq.producer.Producer;
import com.ydcqy.ymq.util.UnsafeUtil;
import sun.misc.Unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
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
        final AtomicInteger n = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {

//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
                    try {
                        ActiveMqConfiguration cfg = new ActiveMqConfiguration();
                        cfg.setUsername("张三");
                        cfg.setPassword("123abc");
                        producer.send(new ActiveMqQueue("com.test", ActiveMqQueue.Type.QUEUE), new ActiveMqMessage(cfg));
                    } catch (MqException e) {
                        e.printStackTrace();
                    }
                    System.out.println(n.incrementAndGet());
//                }
//            });
        }


    }
}
