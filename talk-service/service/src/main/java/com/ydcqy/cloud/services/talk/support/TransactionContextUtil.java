package com.ydcqy.cloud.services.talk.support;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;

import java.sql.SQLException;

/**
 * @author xiaoyu
 */
public class TransactionContextUtil {
    public static void setAutoCommit(SqlSessionFactory sf, boolean autoCommit) throws SQLException {
        SqlSessionUtils.getSqlSession(sf).getConnection().setAutoCommit(autoCommit);
    }
}
