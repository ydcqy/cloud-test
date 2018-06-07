package com.ydcqy.grpc.support;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author xiaoyu
 */
@Slf4j
public final class GrpcServiceContainer {
    private final List<BindableService> serviceImplList = new ArrayList<>();
    private int serverPort = 18100;
    private Server server;

    public GrpcServiceContainer() {
    }

    public GrpcServiceContainer(int listenPort) {
        this.serverPort = listenPort;

    }

    private void initServer() {
        if (server != null) {
            return;
        }
        NettyServerBuilder serverBuilder = (NettyServerBuilder) ServerBuilder.forPort(serverPort);
        for (BindableService bindableService : serviceImplList) {
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

    public void append(BindableService service) {
        serviceImplList.add(service);
    }

    public int size() {
        return serviceImplList.size();
    }

}