package rpc;

import ch.qos.logback.classic.Level;
import com.ydcqy.ynet.rpc.HelloService;
import com.ydcqy.ynet.rpc.YrpcClient;
import com.ydcqy.ynet.rpc.YrpcRequest;
import com.ydcqy.ynet.rpc.YrpcResponse;
import com.ydcqy.ynet.util.LogLevelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class RpcClientTest {
    private static final Logger LOG = LoggerFactory.getLogger(RpcClientTest.class);

    public static void main(String[] args) throws Exception {
        LogLevelUtils.setRootLevel(Level.INFO);

        YrpcClient client = new YrpcClient("127.0.0.1", 1111);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        AtomicInteger n = new AtomicInteger();
        for (int i = 0; i < 1; i++) {
//            Thread.sleep(1);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        YrpcRequest request = new YrpcRequest();
                        String s = n.incrementAndGet() + "";
                        request.setRequestId(s);
                        request.setInterfaceName(HelloService.class.getName());
                        request.setMethodName("sayHiJson");
//                        request.setParams(Arrays.asList(StringValue.newBuilder().setValue("张三哈哈哈哈或或或或").build()
//                                , Int32Value.newBuilder().setValue(101).build()
//                        ).toArray());
                        request.setParams(Arrays.asList("张三", 123).toArray());

                        YrpcResponse response = client.send(request);
                        LOG.info("结果: requestId: {}, result: {}, errMsg: {}", response.getRequestId(), response.getResult(), response.getErrMsg());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        LockSupport.park();
    }
}
