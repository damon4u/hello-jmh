package com.damon4u.jmh;

import lombok.Data;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author bjweidongjun
 * @version 2019-02-28 10:58
 */
@State(Scope.Thread)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1, batchSize = 100)
@Measurement(iterations = 30, time = 1, batchSize = 100)
@Fork(10)
public class LogBenchmark {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogBenchmark.class);

    private LogEntity logEntity = new LogEntity();

    @Benchmark
    public void placeHolderWithDebugCheck() {
        if (LOGGER.isDebugEnabled()) {
            logPlaceHolderDebug();
        }
    }

    @Benchmark
    public void placeHolder() {
        logPlaceHolderDebug();
    }

    @Benchmark
    public void concatWithDebugCheck() {
        if (LOGGER.isDebugEnabled()) {
            logConcatDebug();
        }
    }

    @Benchmark
    public void concat() {
        logConcatDebug();
    }

    @Benchmark
    public void placeholderThroughput() {
        logPlaceHolderInfo();
    }

    @Benchmark
    public void concatThroughput() {
        logConcatInfo();
    }
    
    private void logPlaceHolderDebug() {
        LOGGER.debug("id:{},name:{},ip:{},url:{}", 
                logEntity.getId(), 
                logEntity.getName(), 
                logEntity.getIp(), 
                logEntity.getUrl());
    }

    private void logPlaceHolderInfo() {
        LOGGER.info("id:{},name:{},ip:{},url:{}", 
                logEntity.getId(), logEntity.getName(), 
                logEntity.getIp(), logEntity.getUrl());
    }

    private void logConcatDebug() {
        LOGGER.debug("id:" + logEntity.getId() + ",name:" + logEntity.getName() + ",ip:" + logEntity.getIp() + ",url:" + logEntity.getUrl());
    }

    private void logConcatInfo() {
        LOGGER.info("id:" + logEntity.getId() 
                + ",name:" + logEntity.getName() 
                + ",ip:" + logEntity.getIp() 
                + ",url:" + logEntity.getUrl());
    }

    @Data
    private static class LogEntity {

        private String id;

        private String name;

        private String ip;

        private String url;
    }
}
