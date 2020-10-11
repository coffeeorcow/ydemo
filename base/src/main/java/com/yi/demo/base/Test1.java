package com.yi.demo.base;


public class Test1 {
    public static void main(String[] args) {
        System.out.println(new Test1().isPalindrome(123321));
    }

    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        int len = str.length();
        boolean flag = true;

        if (x > 0 && x < 10) {
            return flag;
        }

        for (int i = 0; i < len / 2; i++) {
            int end = len - i - 1;
            if (i < end && str.charAt(i) != str.charAt(end)) {
                flag = false;
                break;
            }
        }

        return flag;
    }

}
