package com.ydcqy.ycache.cluster;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyu
 */
public abstract class AbstractLoadBalance implements LoadBalance {
    private List<Node> nodes = new ArrayList<>();

    protected abstract Node analyze(List<Node> nodes);

    @Override
    public Node select() {
        if (nodes.size() == 0) return null;
        if (nodes.size() == 1) return nodes.get(0);
        return analyze(nodes);
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }
}
