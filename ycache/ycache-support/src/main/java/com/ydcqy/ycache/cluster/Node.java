package com.ydcqy.ycache.cluster;

import lombok.Data;

/**
 * @author xiaoyu
 */
@Data
public class Node {
    private String host;
    private int port;

    public Node() {
    }

    public Node(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
