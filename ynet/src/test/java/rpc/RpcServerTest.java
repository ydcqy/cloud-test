package rpc;

import ch.qos.logback.classic.Level;
import com.ydcqy.ynet.rpc.HelloService;
import com.ydcqy.ynet.rpc.HelloServiceImpl;
import com.ydcqy.ynet.rpc.YrpcServer;
import com.ydcqy.ynet.rpc.config.ServerConfig;
import com.ydcqy.ynet.util.LogLevelUtils;

import java.util.Scanner;

/**
 * @author xiaoyu
 */
public class RpcServerTest {
    public static void main(String[] args) {
        LogLevelUtils.setRootLevel(Level.INFO);

        ServerConfig config = new ServerConfig();
        config.addService(HelloService.class.getName(), new HelloServiceImpl());

        YrpcServer yrpcServer = new YrpcServer(1111, config);

        System.out.println("启动结果：" + yrpcServer.isOpen());
        while (new Scanner(System.in).nextLine() != null) {
            System.out.println(yrpcServer.getClientChannelMap().size());
        }
    }
}
