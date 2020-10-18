package com.yi.demo.algo.question;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

public class chapter3_1 {

    public static <E> void printLots(List<E> L, List<Integer> P) {
        int curSize = 0;
        for (Integer e : P) {
            while (curSize < L.size()) {
                if (e.equals(curSize)) {
                    System.out.println(L.get(curSize));
                    curSize++;
                    break;
                }
                curSize++;
            }
        }
    }

    public static <E> void printLotsByIterator(List<E> L, List<Integer> P) {
        Iterator<E> iterL = L.iterator();
        Iterator<Integer> iterP = P.iterator();

        Integer pos = null;
        E cur = null;
        int curPos = -1;

        while (iterL.hasNext()) {
            cur = iterL.next();
            curPos++;

            if (pos == null) {
                if (iterP.hasNext()) {
                    pos = iterP.next();
                } else {
                    return;
                }
            }

            if (pos == curPos) {
                System.out.println(cur);
                pos = null;
            }
        }
    }


    public static void main(String[] args) {
        List<String> L = Lists.newArrayList("ni", "xxx", "hao", "fsadf", "fasf", "ya");
        List<Integer> P = Lists.newArrayList(0, 2, 5);

        printLots(L, P);
        printLotsByIterator(L, P);
    }

}
