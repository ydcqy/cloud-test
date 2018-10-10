package com.ydcqy.bigdata.mapreduce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author xiaoyu
 */
public class ChatMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String str = value.toString();
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(str).getJSONObject("nodeContent");
        } catch (Exception e) {
        }
        if (jsonObject != null) {
            String nickName = jsonObject.getString("nickName");
            String content = jsonObject.getString("content");
            if (StringUtils.isNotBlank(nickName)) {
                context.write(new Text(nickName), new Text(content));
            }
        }
    }


}
