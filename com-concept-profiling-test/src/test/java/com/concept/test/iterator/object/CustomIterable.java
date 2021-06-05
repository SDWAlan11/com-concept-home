package com.concept.test.iterator.object;

import java.util.Iterator;
import java.util.List;

public class CustomIterable implements java.lang.Iterable<Integer> {

    private CustomIterator iterator;
    private List<Integer> lista;

    public CustomIterable(List<Integer> lista){
        this.lista = lista;
    }

    @Override
    public Iterator<Integer> iterator() {
        return getIterator();
    }

    private Iterator<Integer> getIterator() {
        if(iterator == null)
            return new CustomIterator(this.lista);
        else
            return iterator;
    }
}
