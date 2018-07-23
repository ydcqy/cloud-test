package com.ydcqy.ymq.connection;

import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.exception.ConnectionException;

/**
 * @author xiaoyu
 */
public interface ConnectionFactory {
    Connection getConnection() throws ConnectionException;

    Configuration getConfiguration();

}
