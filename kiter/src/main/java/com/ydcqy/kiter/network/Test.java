package com.ydcqy.kiter.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
        ByteBuf byteBuf = Unpooled.copiedBuffer("abcd", CharsetUtil.UTF_8);
        System.out.println(byteBuf.writerIndex());
        byteBuf.writeCharSequence("1234", CharsetUtil.UTF_8);
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println(byteBuf.readerIndex(3).toString(CharsetUtil.UTF_8));
        byteBuf.setCharSequence(0, "xx", CharsetUtil.UTF_8);
        byteBuf.readerIndex(0);
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        byteBuf.readChar();
        System.out.println(byteBuf.isDirect());
        System.out.println(byteBuf.isReadable());
        System.out.println(byteBuf.writerIndex());

        System.out.println(byteBuf.readableBytes());
        byteBuf.retain();
        byteBuf.retain();
        byteBuf.retain();
        ReferenceCountUtil.release(byteBuf);
        System.out.println(byteBuf.refCnt());
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));

    }
}
