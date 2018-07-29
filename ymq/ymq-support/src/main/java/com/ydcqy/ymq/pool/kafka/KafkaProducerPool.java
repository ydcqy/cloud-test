package com.ydcqy.ymq.pool.kafka;

import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.exception.PooledException;
import com.ydcqy.ymq.kafka.KafkaConfiguration;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;

import java.util.Properties;

/**
 * @author xiaoyu
 */
public class KafkaProducerPool {
    private GenericObjectPool<PooledKafkaProducer> internalPool;

    public KafkaProducerPool(GenericObjectPoolConfig poolConfig, Properties props) {
        if (this.internalPool != null) {
            try {
                closeInternalPool();
            } catch (Exception e) {
            }
        }
        this.internalPool = new GenericObjectPool<PooledKafkaProducer>(new KafkaFactory(props), poolConfig);
    }

    protected void closeInternalPool() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new PooledException("Could not destroy the pool", e);
        }
    }

    public Producer getResource() {
        try {
            PooledKafkaProducer producer = internalPool.borrowObject();
            producer.setObjectPool(internalPool);
            return producer;
        } catch (Exception e) {
            throw new PooledException("Could not get a resource from the pool", e);
        }
    }
}
