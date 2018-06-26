package com.ydcqy.ycache.cluster.loadbalance;

import com.ydcqy.ycache.cluster.AbstractLoadBalance;
import com.ydcqy.ycache.cluster.Node;

import java.util.List;
import java.util.Random;

/**
 * @author xiaoyu
 */
@SuppressWarnings("unused")
public class RandomLoadBalance extends AbstractLoadBalance {
    private Random random = new Random();

    @Override
    protected Node analyze(List<Node> nodes) {
        return nodes.get(random.nextInt(nodes.size()));
    }
}
