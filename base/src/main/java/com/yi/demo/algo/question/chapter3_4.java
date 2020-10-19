package com.yi.demo.algo.question;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

public class chapter3_4 {

    private static List<Integer> intersections(List<Integer> list1, List<Integer> list2) {
        Iterator<Integer> iter1 = list1.iterator();
        Iterator<Integer> iter2 = list2.iterator();
        List<Integer> list = Lists.newArrayList();

        // todo: 两个 list 求交集
//        Integer value1, value2 = null;
//        while (iter1.hasNext() && iter2.hasNext()) {
//            if (value == null) value = iter1.next();
//
//            if (value.equals(iter2.next())) {
//                list.add(value);
//                value = iter1.next();
//            }
//
//        }

        return null;
    }


    public static void main(String[] args) {
        Lists.newArrayList(1,2,3,4,7,8);
        Lists.newArrayList(1,5,7,9);



    }

}
