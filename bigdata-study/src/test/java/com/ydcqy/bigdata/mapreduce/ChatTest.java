package com.ydcqy.bigdata.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author xiaoyu
 */
public class ChatTest {

    @Test
    public void chatMapperTest() throws Exception {
        Text text = new Text("{\"api\":\"/add\",\"nodeContent\":\"{\\\"content\\\":\\\"wxid_12svr6ybumms22:\\\\\\\\n今天有直播间？\\\",\\\"createTime\\\":\\\"1538206113000\\\",\\\"dataType\\\":\\\"text\\\",\\\"isSend\\\":\\\"0\\\",\\\"msgId\\\":\\\"716\\\",\\\"nickName\\\":\\\"源达_石壮壮\\\",\\\"talker\\\":\\\"8614076852@chatroom\\\",\\\"userName\\\":\\\"wxid_1zcjsj2vez522\\\"}\",\"nodePath\":\"/wechat/2018/09/29/源达_石壮壮/716\"}");
        new MapDriver<LongWritable, Text, Text, Text>()
                .withMapper(new ChatMapper())
                .withInput(new LongWritable(0), text)
                .withOutput(new Text("add"), new Text("源达_石壮壮"))
                .runTest();
    }

    @Test
    public void chatReducerTest() throws Exception {
        Text text = new Text("源达_石壮壮");
        new ReduceDriver<Text, Text, Text, LongWritable>()
                .withReducer(new ChatReducer())
                .withInput(new Text("add"), Arrays.asList(text))
                .withOutput(new Text("add"), new LongWritable(1))
                .runTest();
    }
}
