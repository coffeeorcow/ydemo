package com.yi.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private final int port;

    public NIOServer(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        Selector bossSelector = Selector.open();
        Selector workerSelector = Selector.open();

        new Thread(() -> {
            try {
                ServerSocketChannel serverChannel = ServerSocketChannel.open();
                serverChannel.socket().bind(new InetSocketAddress(this.port));
                serverChannel.configureBlocking(false);
                serverChannel.register(bossSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                    if (bossSelector.select(1) > 0) {
                        Set<SelectionKey> keys = bossSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = keys.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isAcceptable()) {
                                try {
                                    SocketChannel client = ((ServerSocketChannel) key.channel()).accept();
                                    client.configureBlocking(false);
                                    client.register(workerSelector, SelectionKey.OP_READ);
                                } finally {
                                    keyIterator.remove();
                                }
                            }
                        }
                    } else {
                        System.out.println("boss ...");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    if (workerSelector.select(1) > 0) {
                        Set<SelectionKey> keys = workerSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = keys.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isReadable()) {
                                try {
                                    SocketChannel client = (SocketChannel) key.channel();
                                    ByteBuffer buf = ByteBuffer.allocate(1023);
                                    client.read(buf);
                                    buf.flip();
                                    System.out.println(StandardCharsets.UTF_8.decode(buf));
                                } finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    } else {
                        System.out.println("worker ...");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void main(String[] args) throws IOException {
        new NIOServer(8080).run();
    }

}
