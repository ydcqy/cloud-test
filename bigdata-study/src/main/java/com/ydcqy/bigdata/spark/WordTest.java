//package com.ydcqy.bigdata.spark;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.FlatMapFunction;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//import scala.Tuple2;
//
//import java.util.Arrays;
//
//
//public class WordTest {
//    public static void main(String[] args) {
//        // 创建SparkConf
//        SparkConf conf = new SparkConf().setAppName("SortWordCount").setMaster("local");
//        // 创建JavaSparkContext
//        JavaSparkContext sc = new JavaSparkContext(conf);
//
//        JavaRDD<String> lines = sc.textFile("C:\\Users\\xiaoyu\\Desktop\\abc.txt");
//
//        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Iterable<String> call(String t) throws Exception {
//                return Arrays.asList(t.split(" "));
//            }
//        });
//
//        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Tuple2<String, Integer> call(String v1) throws Exception {
//                return new Tuple2<String, Integer>(v1, 1);
//            }
//        });
//
//        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1 + v2;
//            }
//        });
//        //但到这里为止，就得到了每个单词出现的次数
//        //但是问题是，我们的新需求是按照每个单词出现次数的顺序，降序排列
//        //wordCounts RDD内的元素应该是这种格式的（hello，3）（you，5）
//        //我们需要转换成（3，hello） （5，you）这种格式，才能根据单词出现次数排序
//
//        //进行kay-value的反转映射
//        JavaPairRDD<Integer, String> countWords = wordCounts.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Tuple2<Integer, String> call(Tuple2<String, Integer> t) throws Exception {
//                return new Tuple2<Integer, String>(t._2, t._1);
//            }
//        });
//        JavaPairRDD<Integer, String> sortCountWords = countWords.sortByKey(false);
//
//
//        //再次将value-key进行反转映射
//        JavaPairRDD<String, Integer> sortWordCounts = sortCountWords.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Tuple2<String, Integer> call(Tuple2<Integer, String> t) throws Exception {
//                return new Tuple2<String, Integer>(t._2, t._1);
//            }
//        });
//        sortWordCounts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
//
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public void call(Tuple2<String, Integer> t) throws Exception {
//                System.out.println(t._1 + "  appears " + t._2 + " times");
//            }
//        });
//
//        sc.close();
//    }
//}
