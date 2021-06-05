package com.concept.test.profiling.memory;

import java.util.ArrayList;
import java.util.List;

public class TestReduce {
    public static void main(String[] args) {
        List<Integer> gastos = new ArrayList<Integer>();
        gastos.add(100);
        gastos.add(200);
        gastos.add(300);

        gastos.stream().reduce((acumulador, numero) -> {
            return acumulador + numero;
        }).ifPresent(System.out::println);
    }
}
