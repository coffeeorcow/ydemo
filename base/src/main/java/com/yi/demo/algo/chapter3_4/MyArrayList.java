package com.yi.demo.algo.chapter3_4;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;
    private int length;
    private E[] items;

    public MyArrayList() {
        clear();
    }

    public void clear() {
        capacity = 0;
        length = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return this.length;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < this.capacity) return;

        E[] old = this.items;
        this.items = (E[]) new Object[newCapacity];
        for (int i = 0; i < this.length; i++) {
            this.items[i] = old[i];
        }
        this.capacity = newCapacity;
    }

    public MyArrayList<E> add(E x) {
        add(this.length++, x);
        return this;
    }

    public void add(int idx, E x) {
        if (this.capacity == idx) {
            ensureCapacity(idx * 2 + 1);
        }
        items[idx] = x;
    }

    public E get(int i) {
        return this.items[i];
    }

    public E get(E e) {
        for (int i = 0; i < length; i++) {
            if (this.items[i] == e) {
                return this.items[i];
            }
        }

        return null;
    }
    
    public MyArrayList<E> remove(int idx) {
        if (idx >= length || idx < 0) return this;
        
        for (int i = length - 1; i >= idx; i--) {
            this.items[i - 1] = this.items[i];
        }

        length--;
        return this;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "capacity=" + capacity +
                ", length=" + length +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<E> {

        private int current;

        @Override
        public boolean hasNext() {
            return this.current < length;
        }

        @Override
        public E next() {
            return items[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(12).add(13).add(15).remove(1).add(88);
        System.out.println(list);
    }

}
