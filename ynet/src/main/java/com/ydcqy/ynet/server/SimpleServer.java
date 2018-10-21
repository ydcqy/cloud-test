package com.ydcqy.ynet.server;

import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.codec.SimpleServerCodec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.handler.SimpleServerHandler;

/**
 * @author xiaoyu
 */
public class SimpleServer extends AbstractNettyServer {
    private static final Handler handler = new SimpleServerHandler();

    public SimpleServer(int port) {
        super(port);
    }

    @Override
    public Codec getCodec() {
        return new SimpleServerCodec();
    }

    @Override
    public Handler getHandler() {
        return handler;
    }
    public static void main(String[] args) throws InterruptedException {

//        for (; ; ) {
//            Thread.sleep(100);
//            System.out.println(123);
//            System.out.println(222);
//        }
        SimpleServer server = new SimpleServer(1111);
        System.out.println("启动结果：" + server.isOpen());
//        LockSupport.park();
        for (; ; ) {
            try {
                Thread.sleep(2000);
                System.out.println(server.getClientChannelMap().size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
