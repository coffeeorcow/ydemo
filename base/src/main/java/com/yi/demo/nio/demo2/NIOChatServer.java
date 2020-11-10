package com.yi.demo.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NIOChatServer {

    private Selector selector;

    public void run() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));

        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select() <= 0) {
                System.out.println("没有监听到的事件");
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    handleAccept(key);
                } else if (key.isReadable()) {
                    handleRead(key);
                }

                iterator.remove();
            }
        }
    }

    private void handleRead(SelectionKey key){
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                if (!(channel.read(buffer) > 0)) break;
                String msg = new String(buffer.array());
                System.out.println("客户端发送消息 => " + msg);
                sendMsg(channel, msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(SocketChannel originChannel, String msg) {
        for (SelectionKey key : selector.keys()) {
            Channel channel = null;
            try {
                channel = key.channel();
                if (channel instanceof SocketChannel && channel != originChannel) {
                    ((SocketChannel) channel).write(ByteBuffer.wrap(msg.getBytes()));
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    System.out.println("客户端下线...");
                    key.cancel();
                    channel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
        System.out.println("客户端上线， ip => " + sc);
    }

    public static void main(String[] args) throws IOException {
        new NIOChatServer().run();
    }

}
