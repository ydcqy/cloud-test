package com.ydcqy.ycache.cluster;

/**
 * @author xiaoyu
 */
public interface LoadBalance {
    Node select();

    void addNode(Node node);
}

