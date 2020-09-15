package com.yi.demo.base;


import static com.google.common.base.Preconditions.checkNotNull;

public class Test1 {

    public static void main(String[] args) {
        String xx = null;
        String str = checkNotNull(xx, "common");

    }
}
