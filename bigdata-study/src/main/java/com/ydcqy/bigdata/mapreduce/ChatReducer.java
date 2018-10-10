package com.ydcqy.bigdata.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author xiaoyu
 */
public class ChatReducer extends Reducer<Text, Text, Text, LongWritable> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (Text ignored : values) {
            count++;
        }
        context.write(key, new LongWritable(count));
    }
}
