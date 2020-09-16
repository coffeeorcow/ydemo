package com.yi.demo.netty.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyLineReaderHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.printf("接受消息：%s \n", msg);
        LineReaderServer.channels.writeAndFlush("服务端接受消息：" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LineReaderServer.channels.add(ctx.channel());
        System.out.println("新客户端上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LineReaderServer.channels.remove(ctx.channel());
        System.out.println("客户端连接断开");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.printf("出错，错误信息：%s \n", cause.getMessage());
        cause.printStackTrace();
    }
}
