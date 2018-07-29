package com.ydcqy.ymq.message;

import com.ydcqy.ymq.util.NamedThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class MessageExecutor {
    private static final AtomicInteger QUEUE_NUM = new AtomicInteger(1);
    private ExecutorService executor;
    private MessageListener messageListener;
    private ThreadFactory   threadFactory;

    public MessageExecutor(String executorName, int threads) {
        threadFactory = new NamedThreadFactory(executorName + "-" + QUEUE_NUM.getAndIncrement());
        this.executor = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }

    public Future<?> onMessage(Message message) {
        return executor.submit(() -> messageListener.onMessage(message));
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(3));
        executor.execute(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        System.out.println("哈哈哈");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        System.out.println("哈哈哈");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        System.out.println("哈哈哈"); executor.execute(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        System.out.println("哈哈哈");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        System.out.println("哈哈哈");
    }
}
