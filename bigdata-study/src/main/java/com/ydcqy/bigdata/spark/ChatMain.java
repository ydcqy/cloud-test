package com.ydcqy.bigdata.spark;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.lang.reflect.Field;

/**
 * @author xiaoyu
 */
public class ChatMain {
    private static final Logger LOG = LoggerFactory.getLogger(ChatMain.class);

    static {
        try {
            Field rootLoggerField = LoggerContext.class.getDeclaredField("root");
            rootLoggerField.setAccessible(true);
            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) rootLoggerField.get(LoggerFactory.getILoggerFactory());
            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        JavaSparkContext sc = new JavaSparkContext("local", ChatMain.class.getSimpleName(), sparkConf);
        JavaRDD<String> lines = sc.textFile("C:\\Users\\xiaoyu\\Desktop\\chat.log");
        JavaRDD<String> nickNames = lines.map((Function<String, String>) s -> {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSON.parseObject(s).getJSONObject("nodeContent");
            } catch (Exception e) {
            }
            if (jsonObject != null) {
                String nickName = jsonObject.getString("nickName");
                if (StringUtils.isNotBlank(nickName)) {
                    return nickName;
                }
            }
            return null;
        }).filter((Function<String, Boolean>) s -> null == s ? false : true);

        JavaPairRDD<String, Integer> reduce = nickNames
                .mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1))
                .reduceByKey((Function2<Integer, Integer, Integer>) (v1, v2) -> v1 + v2);

        LOG.info("#############结果################");
        reduce.foreach((VoidFunction<Tuple2<String, Integer>>) stringIntegerTuple2 ->
                System.out.println(stringIntegerTuple2._1 + ": " + stringIntegerTuple2._2)
        );
    }
}
