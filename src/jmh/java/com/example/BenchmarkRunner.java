package com.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Fork(value = 1)
@Warmup(iterations = 10, timeUnit = TimeUnit.MILLISECONDS, time = 1000)
@Measurement(iterations = 20, timeUnit = TimeUnit.MILLISECONDS, time = 1000)
public class BenchmarkRunner {
    private static final int SAMPLE_SIZE = 100_000;
    private static final int DAYS_IN_YEAR = 365;
    private static final int GROUP_SIZE = 4_000_000;
    private final BitSetBirthdays bitset = new BitSetBirthdays(DAYS_IN_YEAR, GROUP_SIZE);
    private final BooleanArrayBirthdays boolArray = new BooleanArrayBirthdays(DAYS_IN_YEAR, GROUP_SIZE);
    private final ThreadLocalLoopBirthdays loop = new ThreadLocalLoopBirthdays(DAYS_IN_YEAR, GROUP_SIZE);

    @Benchmark
    public void bitset() {
        bitset.test(SAMPLE_SIZE);
    }

    @Benchmark
    public void booleanArray() {
        boolArray.test(SAMPLE_SIZE);
    }

    @Benchmark
    public void loop() {
        loop.test(SAMPLE_SIZE);
    }
}
