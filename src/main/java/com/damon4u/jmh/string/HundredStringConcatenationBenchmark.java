package com.damon4u.jmh.string;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class HundredStringConcatenationBenchmark {
    private static final int LIST_LENGTH = 100;
    private List<String> strings = new ArrayList<>(LIST_LENGTH);

    @Setup
    public void setupTest(){
        for(int i = 0; i < LIST_LENGTH; i++) {
            strings.add(UUID.randomUUID().toString());
        }
    }

    @Benchmark
    public void testStringFormat(Blackhole bh) {
        String combined = "";
        for(String s : strings) {
            combined = String.format("%s%s", combined, s);
        }
        bh.consume(combined);
    }

    @Benchmark
    public void testPlus(Blackhole bh) {
        String combined = "";
        for(String s : strings) {
            combined = combined + s;
        }
        bh.consume(combined);
    }

    @Benchmark
    public void testStringBuilder(Blackhole bh) {
        StringBuilder sb = new StringBuilder();
        for(String s : strings) {
            sb.append(s);
        }
        bh.consume(sb.toString());
    }

    @Benchmark
    public void testStringBuffer(Blackhole bh) {
        StringBuffer sb = new StringBuffer();
        for(String s : strings) {
            sb.append(s);
        }
        bh.consume(sb.toString());
    }

    @Benchmark
    public void testStringJoin(Blackhole bh) {
        bh.consume(String.join("", strings));
    }

    @Benchmark
    public void testStringConcat(Blackhole bh) {
        String combined = "";
        for(String s : strings) {
            combined = combined.concat(s);
        }
        bh.consume(combined);
    }

    @Benchmark
    public void testStringUtilsJoin(Blackhole bh) {
        bh.consume(StringUtils.join(strings, ""));
    }

    @Benchmark
    public void testGuavaJoiner(Blackhole bh) {
        bh.consume(Joiner.on("").join(strings));
    }

    @Benchmark
    public void testStreamJoining(Blackhole bh) {
        String combined = strings
                .stream()
                .collect(Collectors.joining());
        bh.consume(combined);
    }
}
