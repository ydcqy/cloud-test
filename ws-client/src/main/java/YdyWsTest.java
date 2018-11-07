import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class YdyWsTest {
    public static void main(String[] args) throws Exception {
        WebSocketClient ws = new WebSocketClient(new URI("ws://121.40.165.18:8800")) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println(handshakedata);
            }

            @Override
            public void onMessage(String message) {
                System.out.println(message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println(code + "," + reason + "," + reason);
            }

            @Override
            public void onError(Exception ex) {
                System.out.println(ex);
            }
        };
        ws.connect();
        LockSupport.park();
    }
}
