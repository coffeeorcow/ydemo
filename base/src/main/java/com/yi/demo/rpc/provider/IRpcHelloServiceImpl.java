package com.yi.demo.rpc.provider;

import com.yi.demo.rpc.api.IRpcHelloService;

public class IRpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        return "hello " + name + " !";
    }
}
