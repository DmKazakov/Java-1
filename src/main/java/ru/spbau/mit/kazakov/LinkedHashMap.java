package ru.spbau.mit.kazakov;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedHashMap<K, V> extends AbstractMap<K, V> {
    private Set set;
    private int capacity = 16;
    private int size = 0;
    private LinkedList[] hashtable = new LinkedList[capacity];
    private LinkedList<AbstractMap.Entry> added = new LinkedList<AbstractMap.Entry>();

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    @Override
    public V get(Object o) {
        return super.get(o);
    }

    @Override
    public Set entrySet() {
        return set;
    }

    private class Set extends AbstractSet<AbstractMap.Entry<K, V>> {

        class SetIterator implements Iterator<AbstractMap.Entry<K, V>> {
            AbstractMap.Entry<K, V> value;

            public boolean hasNext() {
                return false;
            }

            public AbstractMap.Entry<K, V> next() {

                return value;
            }

            public void remove() {

            }
        }

        public int size() {
            return size;
        }

        @Override
        public SetIterator iterator() {
            return new SetIterator();
        }
    }

}
