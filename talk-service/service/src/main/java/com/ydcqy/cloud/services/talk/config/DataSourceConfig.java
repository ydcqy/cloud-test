package com.ydcqy.cloud.services.talk.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.ydcqy.cloud.services.talk.support.MultiTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author xiaoyu
 * BeanFactory,FactoryBean,BeanPostProcessor,BeanFactoryPostProcessor
 * EnvironmentAware,InitializingBean,BeanClassLoaderAware,ResourceLoaderAware,BeanNameAware,BeanFactoryAware
 * ApplicationContextInitializer,ApplicationListener,SpringApplicationRunListener
 */

@SuppressWarnings("unused")
public class DataSourceConfig {
    private final static String PIT_DAO_PACKAGE = "com.ydcqy.cloud.services.talk.dao.personalItem";
    private final static String G1_DAO_PACKAGE = "com.ydcqy.cloud.services.talk.dao.g1";

    @Configuration
    @MapperScan(value = PIT_DAO_PACKAGE, sqlSessionFactoryRef = "personalItemSqlSessionFactory")
    public static class PersonalItem {
        @Autowired
        private DataSourceProps.PersonalItem psItemProps;
        @Autowired
        private Environment env;

        @Bean(initMethod = "getConnection")
        @Primary
        @Qualifier("personalItemDateSource")
        @ConfigurationProperties(prefix = DataSourceProps.PersonalItem.PREFIX)
        public DataSource personalItemDateSource() throws Exception {
            return DruidDataSourceFactory.createDataSource(psItemProps.getProps());
        }

        @Bean
        @Primary
        @Qualifier("personalItemSqlSessionFactory")
        public SqlSessionFactory personalItemSqlSessionFactory(@Qualifier("personalItemDateSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
            sfb.setDataSource(dataSource);
            MybatisProperties mybatisProperties = new MybatisProperties();
            mybatisProperties.setMapperLocations(new String[]{env.getProperty("mybatis.mapperLocations")});
            sfb.setMapperLocations(mybatisProperties.resolveMapperLocations());
            return sfb.getObject();
        }

        @Bean
        @Primary
        @Qualifier("personalItemTransactionManager")
        public AbstractPlatformTransactionManager personalItemTransactionManager(@Qualifier("personalItemDateSource") DataSource dataSource) {
            return new MultiTransactionManager(dataSource);
        }

    }

    @Configuration
    @MapperScan(value = G1_DAO_PACKAGE, sqlSessionFactoryRef = "g1SqlSessionFactory")
    public static class G1 {
        @Autowired
        private DataSourceProps.G1 g1Props;
        @Autowired
        private Environment env;

        @Bean(initMethod = "getConnection")
        @Qualifier("g1DateSource")
        @ConfigurationProperties(prefix = DataSourceProps.G1.PREFIX)
        public DataSource g1DateSource() throws Exception {
            return DruidDataSourceFactory.createDataSource(g1Props.getProps());
        }

        @Bean
        @Qualifier("g1SqlSessionFactory")
        public SqlSessionFactory g1SqlSessionFactory(@Qualifier("g1DateSource") DataSource dataSource) throws Exception {
            System.out.println(dataSource + "..哈哈的故事");
            SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();

            sfb.setDataSource(dataSource);
            MybatisProperties mybatisProperties = new MybatisProperties();
            mybatisProperties.setMapperLocations(new String[]{env.getProperty("mybatis.mapperLocations")});
            sfb.setMapperLocations(mybatisProperties.resolveMapperLocations());
            return sfb.getObject();
        }

        @Bean
        @Qualifier("g1TransactionManager")
        public AbstractPlatformTransactionManager g1TransactionManager(@Qualifier("g1DateSource") DataSource dataSource) {
            return new MultiTransactionManager(dataSource);
        }
    }


//    public DataSourceTransactionManager transactionManager

//    @Bean
//    @Qualifier("shopItemDateSource")
//    @ConfigurationProperties(prefix = "spring.datasource.shopItem")
//    public DataSource shopItemDateSource() {
//        log.info("init shopItemDateSource");
//        return new DruidDataSource();
//    }
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
//
//    @Bean
//    public JdbcTemplate channelJdbcTemplate(@Qualifier("channelDateSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public JdbcTemplate shopItemJdbcTemplate(@Qualifier("shopItemDateSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

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
