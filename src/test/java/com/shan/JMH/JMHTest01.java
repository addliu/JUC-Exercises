package com.shan.JMH;

import org.openjdk.jmh.annotations.*;

public class JMHTest01 {

    @Benchmark
    @Warmup(time = 1, iterations = 1)
    @Fork(value = 5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 1)
    public void testForEach() {
        TestStream.foreach();
    }

}
