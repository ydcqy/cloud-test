package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.client.AbstractNettyClient;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.util.SerializationType;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class YrpcClient extends AbstractNettyClient {
//    private static final Logger LOG = LoggerFactory.getLogger(YrpcClient.class);
//
//    static {
//        try {
//            Field rootLoggerField = LoggerContext.class.getDeclaredField("root");
//            rootLoggerField.setAccessible(true);
//            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) rootLoggerField.get(LoggerFactory.getILoggerFactory());
//            rootLogger.setLevel(Level.INFO);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private static final Handler handler = new YrpcClientHandler();

    public YrpcClient(String remoteHost, int remotePort) {
        super(remoteHost, remotePort);
    }

    public YrpcClient(InetSocketAddress remoteAddress) {
        super(remoteAddress);
    }

    @Override
    public Codec getCodec() {
        return new YrpcClientCodec(SerializationType.JSON);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public static void main(String[] args) throws Exception {

        YrpcClient client = new YrpcClient("127.0.0.1", 1111);
        YrpcRequest request = new YrpcRequest();
        request.setRequestId("20181025001");
        request.setInterfaceName("com.ydcqy.yrpc.test.AbcService");
        request.setMethodName("abc");
//        request.setParams(Arrays.asList("111", "哈哈哈 来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j来电管家里见到过j"));
        YrpcResponse response = client.send(request);
        System.out.println(response);

        LockSupport.park();
    }
}
