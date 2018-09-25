package com.ydcqy.ynet.channel;

import java.io.Closeable;

/**
 * @author xiaoyu
 */
public interface Channel extends Closeable {
    /**
     * isOpen
     *
     * @return
     */
    boolean isOpen();
}
