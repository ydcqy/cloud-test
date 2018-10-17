package com.ydcqy.bigdata.hbase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.ydcqy.bigdata.hbase.model.Message;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author xiaoyu
 */

public class Test {
    private static final Logger LOG;

    static {
        ch.qos.logback.classic.Logger logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger(Test.class);
        logger.setLevel(Level.toLevel("INFO"));
        LOG = logger;
    }


    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "10.1.7.11:2181,10.1.7.12:2181");
        Connection connection = ConnectionFactory.createConnection(configuration);
        LOG.info("启动完成");


        TableName tableName = TableName.valueOf(Message.class.getSimpleName());
        Admin admin = connection.getAdmin();

//        if (admin.tableExists(tableName)) {
//            admin.disableTable(tableName);
//            admin.deleteTable(tableName);
//            LOG.info("删除表: {}", tableName);
//        }
//        TableDescriptorBuilder.ModifyableTableDescriptor tableDescriptor = new TableDescriptorBuilder.ModifyableTableDescriptor(tableName);
//        tableDescriptor.setColumnFamily(new ColumnFamilyDescriptorBuilder.ModifyableColumnFamilyDescriptor(Bytes.toBytes(Message.CONTENT)));
//        tableDescriptor.setColumnFamily(new ColumnFamilyDescriptorBuilder.ModifyableColumnFamilyDescriptor(Bytes.toBytes(Message.OWNER)));
//        admin.createTable(tableDescriptor);


        Table table = connection.getTable(tableName);

        Message message = new Message("早上好，你个垃圾", "张三", "李四");
        Put put = new Put(message.getRowKey());
        put.addColumn(Message.CONTENT, null, message.getContent());
        put.addColumn(Message.OWNER, Message.SENDER, message.getSender());
        put.addColumn(Message.OWNER, Message.RECEIVER, message.getReceiver());
        table.put(put);
        LOG.info("#####开始查询#####");
        for (; ; new Scanner(System.in).nextLine()) {
            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                LOG.info("{}: {}-->{}, {}", new String(result.getRow()),
                        new String(result.getValue(Message.OWNER, Message.SENDER)),
                        new String(result.getValue(Message.OWNER, Message.RECEIVER)),
                        new String(result.getValue(Message.CONTENT, null)));
            }
        }
    }
}
