package com.yi.demo.util;

public class TimerUtil {

    private static long curTime;

    public static void start() {
        curTime = System.currentTimeMillis();
    }

    public static void end() {
        System.out.println("花费 " + (System.currentTimeMillis() - curTime) + " ms");
    }

}
