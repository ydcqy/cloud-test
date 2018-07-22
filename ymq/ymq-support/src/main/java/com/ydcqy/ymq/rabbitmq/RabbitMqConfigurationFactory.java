package com.ydcqy.ymq.rabbitmq;

import com.ydcqy.ymq.AbstractConfigurationFactory;
import com.ydcqy.ymq.configuration.Configuration;

/**
 * @author xiaoyu
 */
public class RabbitMqConfigurationFactory extends AbstractConfigurationFactory {
    private static final String                         CONFIG_PREFIX = "rabbitmq";
    private static final Class<? extends Configuration> CONFIG_CLASS  = RabbitMqConfiguration.class;

    @Override
    protected String getConfigPrefix() {
        return CONFIG_PREFIX;
    }

    @Override
    protected Class<? extends Configuration> getConfigurationClass() {
        return CONFIG_CLASS;
    }
}
