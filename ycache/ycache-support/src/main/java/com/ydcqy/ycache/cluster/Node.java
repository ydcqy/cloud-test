package com.ydcqy.ycache.cluster;

import lombok.Data;

/**
 * @author xiaoyu
 */
@Data
public class Node {
    private String host;
    private Integer port;

    public Node() {
    }

    public Node(String host, Integer port) {
        this.host = host;
        this.port = port;
    }
}
