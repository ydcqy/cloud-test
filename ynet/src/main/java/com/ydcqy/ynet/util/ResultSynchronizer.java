package com.ydcqy.ynet.util;

import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.response.Response;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaoyu
 */
public class ResultSynchronizer {
    private static final ConcurrentMap<String, SynchronousQueue<Response>> lockMap = new ConcurrentHashMap<>();
    private String requestId;
    private long timeout;

    public ResultSynchronizer(String requestId, long timeoutMillis) {
        this.requestId = requestId;
        this.timeout = timeoutMillis;
        lockMap.putIfAbsent(requestId, new SynchronousQueue<>());
    }

    public Response get() throws RemoteException {
        try {
            Response response = lockMap.get(requestId).poll(timeout, TimeUnit.MILLISECONDS);
            if (null == response) {
                throw new TimeoutException("Wait result timeout, more than " + timeout + " ms");
            }
            return response;
        } catch (Exception e) {
            throw new RemoteException(e.getMessage(), e);
        } finally {
            lockMap.remove(requestId);
        }
    }
}
