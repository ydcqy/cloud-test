package com.ydcqy.bigdata.hbase.model;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
public class Message {
    public static final byte[] CONTENT = Bytes.toBytes("1");
    public static final byte[] OWNER = Bytes.toBytes("2");
    public static final byte[] SENDER = Bytes.toBytes("1");
    public static final byte[] RECEIVER = Bytes.toBytes("2");
    private AtomicInteger rowKey = new AtomicInteger();

    private String sender;
    private String receiver;
    private String content;

    public Message(String content, String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public byte[] getSender() {
        return Bytes.toBytes(sender);
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public byte[] getReceiver() {
        return Bytes.toBytes(receiver);
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public byte[] getContent() {
        return Bytes.toBytes(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getRowKey() {
        return Bytes.toBytes(sender + "<#>" + receiver + "<#>" + rowKey.incrementAndGet());
    }

}
