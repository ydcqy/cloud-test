package com.ydcqy.ymq;

import com.ydcqy.ymq.configuration.AmqConfiguration;
import com.ydcqy.ymq.configuration.Configuration;

/**
 * @author xiaoyu
 */
public class AmqConfigurationFactory extends AbstractConfigurationFactory {
    private static final String CONFIG_PREFIX = "activemq";
    private static final Class<? extends Configuration> CONFIG_CLASS = AmqConfiguration.class;

    @Override
    protected String getConfigPrefix() {
        return CONFIG_PREFIX;
    }

    @Override
    protected Class<? extends Configuration> getConfigurationClass() {
        return CONFIG_CLASS;
    }
}
