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

    class CustomIterator implements java.util.Iterator<Integer> {
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
            } else
                return element;
        }
    }
}
