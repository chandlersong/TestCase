package me.chandlersong.helloworld;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;

public class SimpleApp {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.createDataset(Arrays.asList("aaaaaa", "bbbbbb"), Encoders.STRING()).cache();

        long numAs = logData.filter(accept("a")).count();
        long numBs = logData.filter(accept("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }

    public static FilterFunction<String> accept(String val) {
        return s -> s.contains(val);
    }
}