package com.concept.test.exam;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class PeekExample {

    int count = 0;

    public static final void main(String[] args) throws RuntimeException{
        List<String> lista= Arrays.asList("hola","que" ,"tal", "estas","tu");


        Stream<Integer> stream = Stream.iterate(1, i-> i+1);
        LongStream.of(9);
        boolean b = stream.noneMatch(i -> i > 5);
        System.out.println(b);

        DoubleStream stream1;

        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(3,9);

        Stream<String> s = Stream.of("speak", "bark", "meow", "growl");
        BinaryOperator<String> merge = (x,y) -> x;
        Map<Integer, String> map21 = s.collect(toMap(String::length, k -> k, merge));
        System.out.println(map21.size() + " " + map21.get(4));
        System.out.println(map21);

        IntStream sa = IntStream.of(200, 300);
        IntSummaryStatistics stats = sa.summaryStatistics();
        Optional<String> optional;
    }
}
