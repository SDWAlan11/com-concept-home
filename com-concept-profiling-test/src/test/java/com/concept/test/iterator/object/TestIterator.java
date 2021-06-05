package com.concept.test.iterator.object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestIterator {
    public static void main(String[] args){
        List<Integer> list = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());

        CustomIterable customIterable = new CustomIterable(list);
        Iterator<Integer> iterator = customIterable.iterator();

        for(Iterator i = customIterable.iterator(); i.hasNext();){
            System.out.println(i.next());
        }

    }
}
