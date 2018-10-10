package com.ydcqy.bigdata.mapreduce;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Arrays;

/**
 * @author xiaoyu
 */
public class ChatJobMain extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("请指定输入输出路径");
        }
        System.exit(ToolRunner.run(new ChatJobMain(), args));
    }


    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance();

        job.setJarByClass(ChatJobMain.class);
        job.setJobName("Chat analysis");

        FileInputFormat.addInputPaths(job, StringUtils.join(",", Arrays.copyOf(args, args.length - 1)));
        job.setMapperClass(ChatMapper.class);
        job.setReducerClass(ChatReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileOutputFormat.setOutputPath(job, new Path(args[args.length - 1]));

        return job.waitForCompletion(true) ? 0 : 1;
    }

}
