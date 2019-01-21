package com.ydcqy.ymq;

import com.ydcqy.ymq.configuration.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author xiaoyu
 */
@Slf4j
public abstract class AbstractConfigurationFactory implements ConfigurationFactory {
    public static String CONFIG_FILE = "combinedmq.yml";

    protected abstract String getConfigPrefix();

    protected abstract Class<? extends Configuration> getConfigurationClass();

    private volatile Configuration configuration;

    private Yaml yaml = new Yaml();

    private void init() {
        synchronized (this) {
            if (this.configuration != null) {
                return;
            }
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE);
            if (null == is) {
//                is = AbstractConfigurationFactory.class.getResourceAsStream(CONFIG_FILE);
            }
            if (null == is) {
                throw new NullPointerException("The configuration file " + CONFIG_FILE + " does not exist");
            }
            Object o = ((Map) yaml.load(is)).get(getConfigPrefix());
            this.configuration = yaml.loadAs(yaml.dump(o), getConfigurationClass());
        }
    }

    @Override
    public Configuration getConfiguration() {
        init();
        return this.configuration;
    }

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        Object o = yaml.load(AbstractConfigurationFactory.class.getResourceAsStream("abc.yml"));
        System.out.println(o);
    }
}
