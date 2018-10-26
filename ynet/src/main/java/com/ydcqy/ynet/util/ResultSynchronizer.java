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

    private ResultSynchronizer() {
    }

    public static void set(String requestId, Response response) throws RemoteException {
        SynchronousQueue<Response> queue = lockMap.get(requestId);
        if (null != queue) {
            try {
                queue.add(response);
            } catch (Exception e) {
                throw new RemoteException(e.getMessage(), e);
            }
        }
    }


    public static Response get(String requestId, long timeoutMillis) throws RemoteException {
        try {
            SynchronousQueue<Response> queue = new SynchronousQueue<>();
            lockMap.putIfAbsent(requestId, queue);
            Response response = lockMap.get(requestId).poll(timeoutMillis, TimeUnit.MILLISECONDS);
            if (null == response) {
                throw new TimeoutException("Wait result timeout, more than " + timeoutMillis + " ms");
            }
            return response;
        } catch (Exception e) {
            throw new RemoteException(e.getMessage(), e);
        } finally {
            lockMap.remove(requestId);
        }
    }

}
