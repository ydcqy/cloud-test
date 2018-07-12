package com.ydcqy.ymq.connection;

/**
 * @author xiaoyu
 */
public class ActiveMqConnection implements Connection<javax.jms.Connection> {
    private javax.jms.Connection connection;

    public ActiveMqConnection(javax.jms.Connection connection) {
        this.connection = connection;
    }

    @Override
    public javax.jms.Connection getTargetConnection() {
        return connection;
    }
}
