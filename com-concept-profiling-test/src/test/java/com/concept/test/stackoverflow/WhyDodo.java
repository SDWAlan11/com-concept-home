package com.concept.test.stackoverflow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WhyDodo {

    private interface Dodo { }

    private static class FancyDodo implements Dodo { }

    public static void main(String[] args) {
        final List<Dodo> dodos = Stream.of(new FancyDodo()).collect(Collectors.toList());
        //final List<FancyDodo> fancyDodos = Stream.of(new FancyDodo()).toList();

        //final List<Dodo> noFancyDodos = Stream.of(new FancyDodo()).toList();
    }
}