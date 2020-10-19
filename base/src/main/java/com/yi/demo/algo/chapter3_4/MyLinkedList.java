package com.yi.demo.algo.chapter3_4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {
    private int theSize;
    private int modCount = 0;
    private Node<E> beginMarker;
    private Node<E> endMarker;

    public static class Node<E> {
        public E value;
        public Node<E> prev;
        public Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        clear();
    }

    public MyLinkedList<E> add(E e) {
        addBefore(getNode(theSize), e);
        return this;
    }

    public void addBefore(Node<E> n, E e) {
        Node<E> newNode = new Node<>(e, n.prev, n);
        n.prev.next = newNode;
        n.prev = newNode;

        theSize++;
        modCount++;
    }

    public boolean contains(E e) {
        Node<E> cur = beginMarker.next;
        while (cur != null && cur != endMarker) {
            if (cur.value.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E get(int index) {
        return getNode(index).value;
    }

    public Node<E> getNode(int index) {
        if (index < 0 || index > theSize) throw new IndexOutOfBoundsException();

        Node<E> e;
        if (index < theSize / 2) {
            e = beginMarker.next;
            for (int i = 0; i < theSize; i++) e = e.next;
        } else {
            e = endMarker;
            for (int i = theSize; i > index; i--) e = e.prev;
        }

        return e;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void clear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public MyLinkedList<E> remove(int idx) {
        remove(getNode(idx));
        return this;
    }

    private void remove(Node<E> n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;

        theSize--;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node<E> cur  = beginMarker.next; cur != endMarker; cur = cur.next) {
            if (cur.prev != beginMarker) sb.append(',');
            sb.append(cur.value != null ? cur.value : "null");
        }
        sb.append(']');

        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> currentNode = beginMarker.next;
        private int expectedCount = modCount;
        private boolean okToRemove = false;


        @Override
        public boolean hasNext() {
            return currentNode != endMarker;
        }

        @Override
        public E next() {
            if (modCount != expectedCount) throw new ConcurrentModificationException();
            else if (!hasNext()) throw new NoSuchElementException();

            E value = currentNode.value;
            currentNode = currentNode.next;
            okToRemove = true;

            return value;
        }

        @Override
        public void remove() {
            if (modCount != expectedCount) throw new ConcurrentModificationException();
            if (!okToRemove) throw new IllegalStateException();

            MyLinkedList.this.remove(currentNode.prev);
            okToRemove = false;
            expectedCount++;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1).add(2).add(3).remove(2).add(2);
        System.out.println(list);

    }

}
