package com.concept.test.profiling.memory;

import java.util.stream.IntStream;

public class CountDiv {

    int divisorsInRange(int min, int max, int div) {
        if (min > max)
            return 0;
        else
            return (min % div == 0 ? 1 : 0) + divisorsInRange(min + 1, max, div);
    }

    int divisor(int min, int max, int div) {
        return (int) IntStream.range(min, max).filter(n -> n % div == 0).count();
    }

    public static void main(String[] args){

    }
}
