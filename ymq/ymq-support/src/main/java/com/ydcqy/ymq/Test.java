package com.ydcqy.ymq;

import kafka.admin.TopicCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.1.7.11:9092,10.1.7.12:9092,10.1.7.13:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024 * 1024);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1000);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        AtomicInteger n = new AtomicInteger(1);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            long ss = System.currentTimeMillis();
            producer.send(new ProducerRecord("n.n.n.n", "王海龙打暑假工"))  ;
            System.out.println(n.getAndIncrement() + "耗时:" + (System.currentTimeMillis() - ss));
        }
        System.out.println("时间:" + (System.currentTimeMillis() - l));
        LockSupport.park();
    }

}

