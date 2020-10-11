package com.yi.demo.algo.chapter2_4;

public class Search {

    /**
     * 有序数组二分查找
     */
    public int binarySearch(int[] a, int value) {
        if (a.length <= 0) return Integer.MIN_VALUE;

        int start = 0, end = a.length - 1;
        while (start <= end && start >= 0 && end < a.length) {
            int mid = (start + end) / 2;

            if (a[mid] == value) {
                return a[mid];
            } else if (a[mid] > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        Search s = new Search();
        System.out.println(s.binarySearch(new int[]{1,2,3,4,5,8,13,15}, 0));
    }

}
