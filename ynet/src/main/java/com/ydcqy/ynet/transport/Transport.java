package com.ydcqy.ynet.transport;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;

import java.io.Closeable;
import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public interface Transport extends Closeable {
    boolean isOpen();

    boolean isSSL();

    boolean isTransportable();

    InetSocketAddress getLocalAddress();

    Channel getChannel();

    Codec getCodec();

    Handler getHandler();
}
