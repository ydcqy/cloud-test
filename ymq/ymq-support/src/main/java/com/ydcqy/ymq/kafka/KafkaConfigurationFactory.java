package com.ydcqy.ymq.kafka;

import com.ydcqy.ymq.AbstractConfigurationFactory;
import com.ydcqy.ymq.configuration.Configuration;

/**
 * @author xiaoyu
 */
public class KafkaConfigurationFactory extends AbstractConfigurationFactory {
    private static final String CONFIG_PREFIX = "kafka";
    private static final Class<? extends Configuration> CONFIG_CLASS = KafkaConfiguration.class;

    @Override
    protected String getConfigPrefix() {
        return CONFIG_PREFIX;
    }

    @Override
    protected Class<? extends Configuration> getConfigurationClass() {
        return CONFIG_CLASS;
    }
}
