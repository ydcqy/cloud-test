package com.ydcqy.grpc;

import com.ydcqy.grpc.service.impl.HelloWorldServiceImpl;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyu
 */
@Data
@Slf4j
public class GrpcServiceMain {

    private final static int SERVER_PORT = 18100;

    private final static List<BindableService> SERVICE_IMPL_LIST = new ArrayList<>();

    static {
        SERVICE_IMPL_LIST.add(new HelloWorldServiceImpl());
    }


    private GrpcServiceMain() {
    }

    static class GrpcServer {
        private Server server;

        public GrpcServer() {
            ServerBuilder<?> serverBuilder = ServerBuilder.forPort(SERVER_PORT);
            for (BindableService bindableService : SERVICE_IMPL_LIST) {
                serverBuilder.addService(bindableService);
            }
            server = serverBuilder.build();
        }

        public void start() throws IOException {
            server = server.start();
            log.info("Server started, listening on " + SERVER_PORT);
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    GrpcServer.this.stop();
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
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.await();
    }
}
