package com.concept.test.iterator.object;

import java.util.Iterator;
import java.util.List;

public class CustomIterator implements java.util.Iterator<Integer> {
    private Iterator<Integer> iterator;

    public CustomIterator(List<Integer> list) {
        this.iterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        Integer element = iterator.next();
        if (element % 5 != 0) {
            return next();
        }
        else
            return element;
    }
}
