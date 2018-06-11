package com.ydcqy.grpc.support;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.netty.NettyServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 用于装Grpc服务端 {@link BindableService} 实现的容器
 *
 * @author xiaoyu
 */
@Slf4j
public final class GrpcServiceContainer {
    private final List<BindableService> serviceImplList = new LinkedList<>();
    private Integer serverPort;
    private Server server;
    private ServerInterceptor interceptor;

    public GrpcServiceContainer(int listenPort) {
        this.serverPort = listenPort;
    }

    private void initServer() {
        if (server != null) {
            return;
        }
        NettyServerBuilder serverBuilder = (NettyServerBuilder) ServerBuilder.forPort(serverPort);
        for (BindableService bindableService : serviceImplList) {
            if (null != interceptor) {
                serverBuilder.addService(ServerInterceptors.intercept(bindableService, this.interceptor));
                continue;
            }
            serverBuilder.addService(bindableService);
        }
        server = serverBuilder.build();
    }

    public void start() throws IOException {
        initServer();
        server = server.start();
        log.info("GrpcServer started, listening on " + serverPort);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                GrpcServiceContainer.this.stop();
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void await() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public void handleThreadPool(Integer threadNum) throws Exception {
        Class<GrpcUtil> grpcUtilClass = GrpcUtil.class;
        Field field = grpcUtilClass.getDeclaredField("SHARED_CHANNEL_EXECUTOR");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, new SharedResourceHolder.Resource<ExecutorService>() {
            private static final String NAME = "grpc-server";

            @Override
            public ExecutorService create() {
                return new ThreadPoolExecutor(threadNum, threadNum,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat(NAME + "-%d").build());
            }

            @Override
            public void close(ExecutorService instance) {
                instance.shutdown();
            }

            @Override
            public String toString() {
                return NAME;
            }
        });
        modifiersField.setAccessible(false);
    }

    public void append(BindableService service) {
        serviceImplList.add(service);
    }

    public int size() {
        return serviceImplList.size();
    }

    public void setInterceptor(ServerInterceptor interceptor) {
        this.interceptor = interceptor;
    }


}