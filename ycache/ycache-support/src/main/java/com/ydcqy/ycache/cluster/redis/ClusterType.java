package com.ydcqy.ycache.cluster.redis;

import java.util.Arrays;

/**
 * @author xiaoyu
 */
public enum ClusterType {
    CODIS,
    REDIS_CLUSTER,
    NONE;

    public static boolean contains(String type) {
        ClusterType[] values = values();
        for (ClusterType value : values) {
            if (value.toString().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    public static ClusterType as(String type) {
        ClusterType[] values = values();
        for (ClusterType value : values) {
            if (value.toString().equalsIgnoreCase(type)) {
                return value;
            }
        }
        return null;
    }
}

