package com.damon4u.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author bjweidongjun
 * @version 2019-02-28 10:58
 */
@State(Scope.Thread)
public class TestBenchmark {

    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 10, time = 5)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Benchmark
    public void test1() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Warmup(iterations = 5, time = 5, batchSize = 20)
    @Measurement(iterations = 10, time = 5, batchSize = 20)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Benchmark
    public void test2() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 10, time = 5)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Benchmark
    public void test3() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 10, time = 5)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Benchmark
    public void test4() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }


}
