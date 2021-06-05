package com.concept.test.exam;

import java.util.Arrays;
import java.util.List;

public class ArrayFill {

    public ArrayFill(){
        super();
        int i = 0;
    }


    int a,b,c;
    public static void main(String[] args){
        String[] array = new String[5];
        Arrays.fill(array, "Hello");
        System.out.println(array[2]);
        List<String> names = null;
        int s  = 4;
        //s++;
        names.removeIf(d -> d.length()>s);


    }
}
