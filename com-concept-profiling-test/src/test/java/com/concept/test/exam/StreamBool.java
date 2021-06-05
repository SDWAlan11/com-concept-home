package com.concept.test.exam;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

public class StreamBool {
    public static void main(String[] args) {
        Stream<Boolean> bools = Stream.iterate(true, b -> !b);
        Map<Boolean, List<Boolean>> map = bools.limit(1)
                .collect(partitioningBy(b -> b));
        System.out.println(map);
        //bools.forEach(System.out::println);
    }
}
