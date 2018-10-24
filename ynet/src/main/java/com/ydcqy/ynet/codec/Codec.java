package com.ydcqy.ynet.codec;

/**
 * @author xiaoyu
 */
public interface Codec {
    /**
     * encode
     *
     * @param message
     * @return
     */
    byte[] encode(Object message);

    /**
     * decode
     *
     * @param bytes
     * @return
     */
    Object decode(byte[] bytes);
}

