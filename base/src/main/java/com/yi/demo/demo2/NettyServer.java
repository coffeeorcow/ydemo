package com.yi.demo.demo2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    private final int port;
    public NettyServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bG = new NioEventLoopGroup();
        EventLoopGroup wG = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bG, wG)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new MyChannelInitializer());

        try {
            ChannelFuture future = b.bind(this.port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wG.shutdownGracefully();
            bG.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new NettyServer(8080).run();
    }
}
