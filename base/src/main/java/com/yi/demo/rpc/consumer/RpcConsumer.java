package com.yi.demo.rpc.consumer;

import com.yi.demo.rpc.api.IRpcHelloService;
import com.yi.demo.rpc.api.IRpcService;
import com.yi.demo.rpc.consumer.proxy.RpcProxy;


public class RpcConsumer {

    public static void main(String[] args) {
        IRpcHelloService rpcHello = RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpcHello.hello("Siri"));

        IRpcService service = RpcProxy.create(IRpcService.class);
        System.out.println(service.add(1, 2));
        System.out.println(service.sub(1, 2));
        System.out.println(service.mul(1, 2));
        System.out.println(service.div(1, 2));
    }

}
