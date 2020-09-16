package com.yi.demo.netty.demo3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class LineReaderInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.printf("客户端上线 ip => %s %d \n", ch.localAddress().getHostName(), ch.localAddress().getPort());

        ch.pipeline()
                .addLast(new LineBasedFrameDecoder(1024))
                .addLast(new StringDecoder(StandardCharsets.UTF_8))
                .addLast(new StringEncoder(StandardCharsets.UTF_8))
                .addLast(new MyLineReaderHandler());


    }

}
