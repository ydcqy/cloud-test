package com.ydcqy.kiter.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lenovo on 2018/1/27.
 */
@Slf4j
public class Test {
    @Data
    static class Abc {
        private String name;
        private int age;
        private Object obj;
    }

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT_SEQ", CharsetUtil.ISO_8859_1));
        byteBuf.clear();
        byteBuf.writeInt(123);

        System.out.println(byteBuf.readerIndex(2).array().length);

    }
}
