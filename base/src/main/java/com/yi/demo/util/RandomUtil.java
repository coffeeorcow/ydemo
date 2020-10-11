package com.yi.demo.util;

import java.util.Arrays;
import java.util.Random;

public class RandomUtil {

    /**
     * 生成指定大小范围的随机数组
     */
    public static int[] randomArray(int min, int max, int len) {
        if (len <= 0) {
            throw new RuntimeException("生成的随机数组长度有误，len => " + len);
        }
        Random rand = new Random();
        int dif = Math.abs(max - min);
        int[] r = new int[len];

        for (int i = 0; i < len; i++) {
            r[i] = rand.nextInt(dif) + min;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(randomArray(-5, 5, 10)));
    }

}
