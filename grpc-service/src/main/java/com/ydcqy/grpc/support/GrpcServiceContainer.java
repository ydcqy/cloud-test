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
    private final int SERVER_PORT = 18100;
    private final List<BindableService> SERVICE_IMPL_LIST = new ArrayList<>();
    private Server server;

    public GrpcServiceContainer() {
    }

    private void initServer() {
        if (server != null) {
            return;
        }
        NettyServerBuilder serverBuilder = (NettyServerBuilder) ServerBuilder.forPort(SERVER_PORT);
        for (BindableService bindableService : SERVICE_IMPL_LIST) {
            serverBuilder.addService(bindableService);
        }
        server = serverBuilder.build();
    }

    public void start() throws IOException {
        initServer();
        server = server.start();
        log.info("GrpcServer started, listening on " + SERVER_PORT);
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
        SERVICE_IMPL_LIST.add(service);
    }

    public int size() {
        return SERVICE_IMPL_LIST.size();
    }

}