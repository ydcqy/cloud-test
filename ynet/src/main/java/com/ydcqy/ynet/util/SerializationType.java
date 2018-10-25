package com.ydcqy.ynet.util;

/**
 * @author xiaoyu
 */
public enum SerializationType {
    PROTO((byte) 1),
    THRIFT((byte) 2),
    JSON((byte) 3),
    JAVA((byte) 4);

    public int bitValue;

    SerializationType(byte bitValue) {
        this.bitValue = bitValue;
    }
}
