package com.yi.demo.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);
        System.out.println("接受消息");
        System.out.println(new String(msgByte, Charset.defaultCharset()));
    }
}
