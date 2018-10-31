package com.ydcqy.ynet.rpc;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import com.ydcqy.ynet.client.AbstractNettyClient;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.util.SerializationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class YrpcClient extends AbstractNettyClient {
    private static final Logger LOG = LoggerFactory.getLogger(YrpcClient.class);

    static {
        try {
            Field rootLoggerField = LoggerContext.class.getDeclaredField("root");
            rootLoggerField.setAccessible(true);
            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) rootLoggerField.get(LoggerFactory.getILoggerFactory());
            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Handler handler = new YrpcClientHandler();

    public YrpcClient(String remoteHost, int remotePort) {
        super(remoteHost, remotePort);
    }

    public YrpcClient(InetSocketAddress remoteAddress) {
        super(remoteAddress);
    }

    @Override
    public Codec getCodec() {
        return new YrpcClientCodec(SerializationType.PROTO);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public static void main(String[] args) throws Exception {

        YrpcClient client = new YrpcClient("127.0.0.1", 1111);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        YrpcRequest request = new YrpcRequest();
                        String s = UUID.randomUUID().toString();
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setInterfaceName(HelloService.class.getName());
                        request.setMethodName("sayHi");
                        request.setParams(Arrays.asList(StringValue.newBuilder().setValue("张三").build()
                                , Int32Value.newBuilder().setValue(101).build()
                        ).toArray());

                        LOG.info("开始: {},requestId: {}", request, s);
                        YrpcResponse response = client.send(request);
                        LOG.info("结果: {}", response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        LockSupport.park();
    }
}
