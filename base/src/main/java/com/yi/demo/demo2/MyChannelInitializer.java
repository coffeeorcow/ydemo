package com.yi.demo.demo2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.printf("客户端上线 ip => %s %d \n", ch.localAddress().getHostName(), ch.localAddress().getPort());

        ch.pipeline().addLast(new MyServerHandler());
    }

}
