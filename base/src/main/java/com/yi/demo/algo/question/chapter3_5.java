package com.yi.demo.algo.question;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

public class chapter3_5 {

    private static <E extends Comparable<E>> List<E> union(List<E> list1, List<E> list2) {
        List<E> list = Lists.newArrayList();
        Iterator<E> iter1 = list1.iterator();
        Iterator<E> iter2 = list2.iterator();
        if (!iter1.hasNext() || !iter2.hasNext()) return list;

        E item1 = iter1.next();
        E item2 = iter2.next();
        E newest = null;

        while (item1 != null || item2 != null) {
            if (item1 == null) {
                list.add(item2);
                item2 = iter1.hasNext() ? iter1.next() : null;
                break;
            } else if (item2 == null) {
                list.add(item1);
                item1 = iter2.hasNext() ? iter2.next() : null;
                break;
            }

            int cmp = item1.compareTo(item2);
            if (cmp < 0) {
                if (newest == null || newest.compareTo(item1) < 0) {
                    list.add(item1);
                    newest = item1;
                }
                item1 = iter1.hasNext() ? iter1.next() : null;
            } else if (cmp == 0) {
                if (newest == null) list.add(item1);
                item1 = iter1.hasNext() ? iter1.next() : null;
                item2 = iter2.hasNext() ? iter2.next() : null;
            } else {
                if (newest == null || newest.compareTo(item2) < 0) {
                    list.add(item2);
                    newest = item2;
                }
                item2 = iter2.hasNext() ? iter2.next() : null;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 7, 8);
        List<Integer> list2 = Lists.newArrayList(1, 5, 7, 9);
        System.out.println(union(list1, list2));
    }

}
