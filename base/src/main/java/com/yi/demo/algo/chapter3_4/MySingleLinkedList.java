package com.yi.demo.algo.chapter3_4;

import java.util.Iterator;

public class MySingleLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public static class Node<E> {
        public Node<E> next;
        public E value;

        public Node(E e) {
            this.value = e;
        }

    }

    public MySingleLinkedList() {
        clear();
    }

    public void clear() {
        head = new Node<>(null);
        tail = null;
        size = 0;
    }

    public MySingleLinkedList<E> append(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            this.head.next = newNode;
            tail = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.size++;
        return this;
    }

}
