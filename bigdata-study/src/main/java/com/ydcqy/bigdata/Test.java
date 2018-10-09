package com.ydcqy.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map;

/**
 * @author xiaoyu
 */
public class Test extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new Test(), args);
    }

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        for (Map.Entry<String, String> entry : conf) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return 0;
    }
}
