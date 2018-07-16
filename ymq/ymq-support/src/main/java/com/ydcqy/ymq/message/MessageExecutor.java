package com.ydcqy.ymq.message;

import com.ydcqy.ymq.util.NamedThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
public class MessageExecutor {
    private static final AtomicInteger QUEUE_NUM = new AtomicInteger(1);
    private Executor executor;
    private MessageListener messageListener;
    private ThreadFactory threadFactory;

    public MessageExecutor(String executorName, int threads) {
        threadFactory = new NamedThreadFactory(executorName + "-" + QUEUE_NUM.getAndIncrement());
        this.executor = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }

    public void onMessage(Message message) {
        executor.execute(() -> messageListener.onMessage(message));
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }
}
