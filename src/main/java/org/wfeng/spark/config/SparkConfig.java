package org.wfeng.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spark配置
 *
 * Created by wfeng on 2018/5/31.
 */
@Configuration
public class SparkConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spark.master.uri}")
    private String masterUri;

    @Value("${spark.executor.memory}")
    private String executorMemory;

    @Value("${spark.cores.max}")
    private String coresMax;

    @Value("${spark.ui.enabled}")
    private String sparkUiControl;

    @Value("${spark.sql.warehouse.dir}")
    private String sparkSqlWarehouseDir;

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
                .setAppName(appName)
                .set("spark.executor.memory",executorMemory)
                .set("spark.cores.max", coresMax)
                .set("spark.ui.enabled", sparkUiControl)
                .set("spark.sql.warehouse.dir", sparkSqlWarehouseDir)
                .setMaster(masterUri);
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SparkSession sparkSession(){
        return SparkSession
                .builder()
                .sparkContext(javaSparkContext().sc())
                .appName("SparkAppBySpringBoot")
                .getOrCreate();
    }
}