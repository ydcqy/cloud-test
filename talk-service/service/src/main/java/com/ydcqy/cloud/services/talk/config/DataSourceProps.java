package com.ydcqy.cloud.services.talk.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author xiaoyu
 */
public final class DataSourceProps {
    @Data
    @Configuration
    public static class PersonalItem {
        public static final String PREFIX = "spring.datasource.personalItem";
        @Value("${" + PREFIX + ".type}")
        private String type;
        @Value("${" + PREFIX + ".driverClassName}")
        private String driverClassName;
        @Value("${" + PREFIX + ".url}")
        private String url;
        @Value("${" + PREFIX + ".username}")
        private String username;
        @Value("${" + PREFIX + ".password}")
        private String password;
        @Value("${" + PREFIX + ".initialSize}")
        private String initialSize;
        @Value("${" + PREFIX + ".minIdle}")
        private String minIdle;
        @Value("${" + PREFIX + ".maxActive}")
        private String maxActive;
        @Value("${" + PREFIX + ".maxWait}")
        private String maxWait;
        @Value("${" + PREFIX + ".timeBetweenEvictionRunsMillis}")
        private String timeBetweenEvictionRunsMillis;
        @Value("${" + PREFIX + ".minEvictableIdleTimeMillis}")
        private String minEvictableIdleTimeMillis;
        @Value("${" + PREFIX + ".validationQuery}")
        private String validationQuery;
        @Value("${" + PREFIX + ".testOnReturn}")
        private String testOnReturn;
        @Value("${" + PREFIX + ".testWhileIdle}")
        private String testWhileIdle;
        @Value("${" + PREFIX + ".testOnBorrow}")
        private String testOnBorrow;
        @Value("${" + PREFIX + ".filters}")
        private String filters;

        public Properties getProps() {
            Properties p = new Properties();
            p.setProperty("type", type);
            p.setProperty("driverClassName", driverClassName);
            p.setProperty("url", url);
            p.setProperty("username", username);
            p.setProperty("password", password);
            p.setProperty("initialSize", initialSize);
            p.setProperty("minIdle", minIdle);
            p.setProperty("maxActive", maxActive);
            p.setProperty("maxWait", maxWait);
            p.setProperty("timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis);
            p.setProperty("minEvictableIdleTimeMillis", minEvictableIdleTimeMillis);
            p.setProperty("validationQuery", validationQuery);
            p.setProperty("testOnReturn", testOnReturn);
            p.setProperty("testWhileIdle", testWhileIdle);
            p.setProperty("testOnBorrow", testOnBorrow);
            p.setProperty("filters", filters);
            return p;
        }
    }

    @Data
    @Configuration
    public static class G1 {
        public static final String PREFIX = "spring.datasource.g1";
        @Value("${" + PREFIX + ".type}")
        private String type;
        @Value("${" + PREFIX + ".driverClassName}")
        private String driverClassName;
        @Value("${" + PREFIX + ".url}")
        private String url;
        @Value("${" + PREFIX + ".username}")
        private String username;
        @Value("${" + PREFIX + ".password}")
        private String password;
        @Value("${" + PREFIX + ".initialSize}")
        private String initialSize;
        @Value("${" + PREFIX + ".minIdle}")
        private String minIdle;
        @Value("${" + PREFIX + ".maxActive}")
        private String maxActive;
        @Value("${" + PREFIX + ".maxWait}")
        private String maxWait;
        @Value("${" + PREFIX + ".timeBetweenEvictionRunsMillis}")
        private String timeBetweenEvictionRunsMillis;
        @Value("${" + PREFIX + ".minEvictableIdleTimeMillis}")
        private String minEvictableIdleTimeMillis;
        @Value("${" + PREFIX + ".validationQuery}")
        private String validationQuery;
        @Value("${" + PREFIX + ".testOnReturn}")
        private String testOnReturn;
        @Value("${" + PREFIX + ".testWhileIdle}")
        private String testWhileIdle;
        @Value("${" + PREFIX + ".testOnBorrow}")
        private String testOnBorrow;
        @Value("${" + PREFIX + ".filters}")
        private String filters;

        public Properties getProps() {
            Properties p = new Properties();
            p.setProperty("type", type);
            p.setProperty("driverClassName", driverClassName);
            p.setProperty("url", url);
            p.setProperty("username", username);
            p.setProperty("password", password);
            p.setProperty("initialSize", initialSize);
            p.setProperty("minIdle", minIdle);
            p.setProperty("maxActive", maxActive);
            p.setProperty("maxWait", maxWait);
            p.setProperty("timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis);
            p.setProperty("minEvictableIdleTimeMillis", minEvictableIdleTimeMillis);
            p.setProperty("validationQuery", validationQuery);
            p.setProperty("testOnReturn", testOnReturn);
            p.setProperty("testWhileIdle", testWhileIdle);
            p.setProperty("testOnBorrow", testOnBorrow);
            p.setProperty("filters", filters);
            return p;
        }
    }
}
