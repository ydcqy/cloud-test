package ws;

import ch.qos.logback.classic.Level;
import com.ydcqy.ynet.util.LogLevelUtils;
import com.ydcqy.ynet.ws.WebSocketInfo;
import com.ydcqy.ynet.ws.WebSocketRequestHandler;
import com.ydcqy.ynet.ws.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author xiaoyu
 */
public class ChatServerTest {
    private static final Logger LOG = LoggerFactory.getLogger(ChatServerTest.class);

    public static void main(String[] args) {
        LogLevelUtils.setRootLevel(Level.INFO);

        WebSocketServer server = new WebSocketServer(2222, new WebSocketRequestHandler() {
            @Override
            public void onOpen(WebSocketInfo info) {
                LOG.info("建立连接: {}", info);
                info.getChannel().send("***** " + info.getQueryParam("name") + "，欢迎光临" + " *****");
                getWebSocketServer().getClientChannelMap().values().forEach(d -> {
                    if (!info.getChannel().equals(d)) {
                        d.send("> " + info.getQueryParam("name") + "，加入聊天");
                    }
                });
            }

            @Override
            public void onMessag(WebSocketInfo info, String message) {
                LOG.info("收到消息, {}: {}", info.getQueryParam("name"), message);
                getWebSocketServer().getClientChannelMap().values().forEach(d -> {
                    if (!info.getChannel().equals(d)) {
                        d.send(info.getQueryParam("name") + ": " + message);
                    }
                });
            }

            @Override
            public void onClose(WebSocketInfo info) {
                LOG.info("关闭连接: {}", info);
                getWebSocketServer().getClientChannelMap().values().forEach(d -> {
                    if (!info.getChannel().equals(d)) {
                        d.send("> " + info.getQueryParam("name") + "，退出聊天");
                    }
                });
            }
        });
        System.out.println("启动结果：" + server.isOpen());
        while (new Scanner(System.in).nextLine() != null) {
            System.out.println(server.getClientChannelMap().size());
        }
    }
}
