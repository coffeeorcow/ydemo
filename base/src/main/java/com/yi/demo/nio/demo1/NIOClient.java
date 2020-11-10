package com.yi.demo.nio.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(8080));

        String greet = "hello!";
        ByteBuffer buffer = ByteBuffer.wrap(greet.getBytes());
        sc.write(buffer);

    }

}
