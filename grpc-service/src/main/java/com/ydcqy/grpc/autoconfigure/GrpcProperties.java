package com.ydcqy.grpc.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaoyu
 */
@Data
@ConfigurationProperties(prefix = "ydcqy.grpc")
public class GrpcProperties {
    private Client client = new Client();
    private Server server = new Server();

    @Data
    public static class Client {
        private Integer port = 8100;
        private String host;
    }

    @Data
    public static class Server {
        private Integer port = 8100;
        private Integer threadNum = 200;
    }

}
