package com.example.springspark;

import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SparkService {
    private SparkSession spark;

    @PostConstruct
    public void init() {
        spark = SparkSession.builder()
                .appName("SpringSparkApp")
                .getOrCreate();
        System.out.println("SparkSession started: " + spark.version());
    }

    public SparkSession getSpark() {
        return spark;
    }
}
