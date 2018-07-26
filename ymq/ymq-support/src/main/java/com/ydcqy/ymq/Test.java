package com.ydcqy.ymq;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.1.7.31:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        AtomicInteger n = new AtomicInteger(1);
        for (int i = 0; i < 100000;i++) {
            producer.send(new ProducerRecord("n.n.n", "王海龙打暑假工")).get();
            System.out.println(n.getAndIncrement());
        }
    }

}

