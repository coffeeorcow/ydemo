package com.yi.demo.algo.question;

import com.yi.demo.algo.chapter3_4.MyLinkedList;

public class chapter3_3 {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1).add(2).add(12).add(5);
        System.out.println(list.contains(1));
        System.out.println(list.contains(5));
        System.out.println(list.contains(33));
    }
}
