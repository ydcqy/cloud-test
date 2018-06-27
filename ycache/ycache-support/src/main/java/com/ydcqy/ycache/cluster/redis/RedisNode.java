package com.ydcqy.ycache.cluster.redis;

import com.ydcqy.ycache.cluster.Node;

/**
 * @author xiaoyu
 */
public class RedisNode extends Node {
    private String password;

    public RedisNode() {
    }

    public RedisNode(String password) {
        this.password = password;
    }

    public RedisNode(String host, Integer port) {
        super(host, port);
    }

    public RedisNode(String host, Integer port, String password) {
        super(host, port);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
