import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author xiaoyu
 */
public class YdyWsTest {
    public static void main(String[] args) throws Exception {

        WebSocketClient ws = new WebSocketClient(new URI("ws://121.40.165.18:8800")) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
    }
}
