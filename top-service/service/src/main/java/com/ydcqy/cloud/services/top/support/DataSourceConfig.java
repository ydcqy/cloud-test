package com.ydcqy.cloud.services.top.support;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author xiaoyu
 *         BeanFactory,FactoryBean,BeanPostProcessor,BeanFactoryPostProcessor
 *         EnvironmentAware,InitializingBean,BeanClassLoaderAware,BeanNameAware,BeanFactoryAware
 *         ApplicationContextInitializer,ApplicationListener,SpringApplicationRunListener
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @Qualifier("channelDateSource")
    @ConfigurationProperties(prefix = "spring.datasource.channel")
    public DataSource channelDateSource() {
        log.info("init channelDateSource");
        return new DruidDataSource();
    }

    @Bean
    @Qualifier("shopItemDateSource")
    @ConfigurationProperties(prefix = "spring.datasource.shopItem")
    public DataSource shopItemDateSource() {
        log.info("init shopItemDateSource");
        return new DruidDataSource();
    }
//
//    @Bean
//    @Qualifier("shopDateSource")
//    @ConfigurationProperties(prefix = "spring.datasource.shop")
//    public DataSource shopDateSource() {
//        return new DruidDataSource();
//    }
//
//    @Bean
//    @Qualifier("openDateSource")
//    @ConfigurationProperties(prefix = "spring.datasource.open")
//    public DataSource openDateSource() {
//        return new DruidDataSource();
//    }

    @Bean
    public JdbcTemplate channelJdbcTemplate(@Qualifier("channelDateSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate shopItemJdbcTemplate(@Qualifier("shopItemDateSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//
//    @Bean
//    public JdbcTemplate openJdbcTemplate(@Qualifier("openDateSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

//    @Configuration
//    @EnableJpaRepositories
//    @EnableTransactionManagement
//    public static class JpaRepositoriesConfig {
//        @Autowired
//        private DataSource channelDateSource;
//
//
//    }


}
