package com.yi.demo.algo.question;

import com.yi.demo.algo.chapter3_4.MyLinkedList;
import com.yi.demo.algo.chapter3_4.MySingleLinkedList;


public class chapter3_2 {

    public static <E> void exchangeSingle(MySingleLinkedList.Node<E> beforeP) {
        if (beforeP == null) return;
        MySingleLinkedList.Node<E> prevNode = beforeP.next;
        if (prevNode == null) return;
        MySingleLinkedList.Node<E> suffixNode = prevNode.next;
        if (suffixNode == null) return;

        prevNode.next = suffixNode.next;
        suffixNode.next = prevNode;
        beforeP.next = suffixNode;
    }

    public static <E> void exchangeDoubleLinkedList(MyLinkedList.Node<E> node) {
        if (node == null || node.next == null) return;

        MyLinkedList.Node<E> suffixNode = node.next;

        node.next = suffixNode.next;
        suffixNode.prev = node.prev;

        suffixNode.next = node;
        node.prev = suffixNode;

        suffixNode.prev.next = suffixNode;
        node.next.prev = node;
    }

    public static void main(String[] args) {
        MySingleLinkedList<String> singleLinkedList = new MySingleLinkedList<>();
        singleLinkedList.append("hi").append("ni").append("hao").append("ya").append("bla");
        System.out.println(singleLinkedList);
        exchangeSingle(singleLinkedList.getNode(1));
        System.out.println(singleLinkedList);


        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(1).add(2).add(3).add(4).add(5);
        System.out.println(linkedList);
        exchangeDoubleLinkedList(linkedList.getNode(2));
        System.out.println(linkedList);
    }

}
