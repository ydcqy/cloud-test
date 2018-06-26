package com.ydcqy.ycache.cluster.redis;

import com.ydcqy.ycache.cluster.Node;
import lombok.Data;

/**
 * @author xiaoyu
 */
@Data
public class RedisNode extends Node {
    private String password;
}
