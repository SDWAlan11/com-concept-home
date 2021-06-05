package com.concept.test.exam;

import java.util.Arrays;
import java.util.List;
public class Principal2 {
    public static void main(String[] args) {
        List<String> lista= Arrays.asList("hola","que" ,"tal", "estas","tu");
        lista.stream()
                .peek((cadena)-> {
                    System.out.println("***inicio****");
                    System.out.println(cadena);
                    System.out.println("****fin inicio****");
                }).filter((cadena)->cadena.length()>3)
                .peek((cadena)-> {
                    System.out.println("-----filtro--------");
                    System.out.println(cadena);
                    System.out.println("-----fin filtro-----");
                })
                .map((cadena)->cadena.toUpperCase()).
                peek((cadena)-> {
                    System.out.println(">>>>>>mayusculas>>>>>>");
                    System.out.println(cadena);
                    System.out.println(">>>>>>>fin mayusculas>>>>>>");
                }).forEach(System.out::println);
    }
}