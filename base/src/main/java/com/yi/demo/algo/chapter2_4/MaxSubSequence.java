package com.yi.demo.algo.chapter2_4;

import com.yi.demo.util.RandomUtil;
import com.yi.demo.util.TimerUtil;

/**
 * 最大公共子序列
 */
public class MaxSubSequence {

    /**
     * O(n^3) 暴力
     */
    public int maxSubSum1(int[] a) {
        int subSum = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int curSum = 0;

                for (int k = i; k <= j; k++) {
                    curSum += a[k];
                }
                if (curSum > subSum) {
                    subSum = curSum;
                }
            }
        }

        return subSum;
    }

    /**
     * O(n^2) 二分递归
     */
    public int maxSubSum2(int[] a) {
        return maxSubSeq(a, 0, a.length - 1);
    }

    private int maxSubSeq(int[] a, int start, int end) {
        if (start >= end) {
            return Math.max(a[start], 0);
        }

        int mid = (start + end) / 2;
        int left = maxSubSeq(a, start, mid);
        int right = maxSubSeq(a, mid + 1, end);

        int leftBorder = 0, leftBorderMax = 0;
        for (int i = mid; i >= start; i--) {
            leftBorder += a[i];

            if (leftBorder > leftBorderMax) leftBorderMax = leftBorder;
        }

        int rightBorder = 0, rightBorderMax = 0;
        for (int j = mid + 1; j <= end; j++) {
            rightBorder += a[j];
            if (rightBorder > rightBorderMax) rightBorderMax = rightBorder;
        }

        int maxBorderWhole = leftBorderMax + rightBorderMax;

        if (left > right) return Math.max(left, maxBorderWhole);
        else return Math.max(right, maxBorderWhole);
    }

    /**
     * O(n)
     */
    public int maxSubSum3(int[] a) {
        int maxSubSum = 0, curSum = 0;
        for (int i = 0; i < a.length; i++) {
            curSum += a[i];

            if (curSum < 0) {
                curSum = 0;
            } else if (curSum > maxSubSum) {
                maxSubSum = curSum;
            }
        }
        return maxSubSum;
    }

    public static void main(String[] args) {
        MaxSubSequence s = new MaxSubSequence();
        int[] seq = RandomUtil.randomArray(-20, 20, 100000);

//        TimerUtil.start();
//        int r1 = s.maxSubSum1(seq);
//        TimerUtil.end();
//        System.out.println(r1);

        TimerUtil.start();
        int r2 = s.maxSubSum2(seq);
        TimerUtil.end();
        System.out.println(r2);

        TimerUtil.start();
        int r3 = s.maxSubSum3(seq);
        TimerUtil.end();
        System.out.println(r3);

    }

}
