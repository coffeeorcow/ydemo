package com.yi.demo.algo.question;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

public class chapter3_4 {

    private static <E extends Comparable<E>> List<E> intersections(List<E> list1, List<E> list2) {
        List<E> list = Lists.newArrayList();
        Iterator<E> iter1 = list1.iterator();
        Iterator<E> iter2 = list2.iterator();
        if (!iter1.hasNext() || !iter2.hasNext()) {
            return list;
        }

        E item1 = iter1.next();
        E item2 = iter2.next();
        while (item1 != null && item2 != null) {
            int cmp = item1.compareTo(item2);
            if (cmp < 0) {
                if (!iter1.hasNext()) break;
                item1 = iter1.next();
            } else if (cmp == 0) {
                list.add(item1);

                if (!iter1.hasNext() || !iter2.hasNext()) break;
                item1 = iter1.next();
                item2 = iter2.next();
            } else {
                if (!iter2.hasNext()) break;
                item2 = iter2.next();
            }
        }
        return list;
    }


    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 7, 8);
        List<Integer> list2 = Lists.newArrayList(1, 5, 7, 9);
        System.out.println(intersections(list1, list2));

    }

}
