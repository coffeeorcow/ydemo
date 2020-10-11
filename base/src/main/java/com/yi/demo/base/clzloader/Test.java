package com.yi.demo.base.clzloader;

public class Test {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
    }



}

class MyClassLoader extends ClassLoader {



}
