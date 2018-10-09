package com.ydcqy.bigdata.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;
import org.apache.log4j.lf5.util.StreamUtils;

import java.io.FileInputStream;
import java.net.URI;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String uri = "file:///C:/Users/xiaoyu/Desktop/新建文本文档.txt";
        if (args.length > 0) {
            uri = args[0];
        }
        Configuration cfg = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), cfg);
        System.out.println(fs);

        FSDataInputStream in = fs.open(new Path(uri));

        StreamUtils.copy(in, System.out);

        FileStatus listStatus = fs.getFileStatus(new Path("file:///C:/Users/xiaoyu/Desktop"));
        System.out.println();
        System.out.println(listStatus);
        FSDataOutputStream out = fs.create(new Path("file:///C:/Users/xiaoyu/Desktop/新建文本文档copy.txt"), new Progressable() {
            @Override
            public void progress() {
                System.out.print(".");
            }
        });
        StreamUtils.copy(new FileInputStream("C:\\Users\\xiaoyu\\Desktop\\新建文本文档.txt"), out);
        out.close();
    }
}
