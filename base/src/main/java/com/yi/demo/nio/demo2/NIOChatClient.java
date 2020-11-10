package com.yi.demo.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class NIOChatClient {

    private Selector selector;
    private String username;
    private SocketChannel sc;

    public void run() throws IOException {
        sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(8080));
        sc.configureBlocking(false);

        selector = Selector.open();
        sc.register(selector, SelectionKey.OP_READ);
        username = sc.getLocalAddress().toString().substring(1);
        System.out.println(username + " is ok...");

        while (true) {
            if (selector.select(1000) <= 0) continue;

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    handleRead(key);
                }
                iterator.remove();
            }
        }

    }

    public void acceptUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sendInfo(line);
        }
    }

    private void sendInfo(String msg) {
        msg = username + " => " + msg;
        try {
            sc.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRead(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            sc.read(buffer);
            System.out.println(new String(buffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        NIOChatClient client = new NIOChatClient();
        new Thread(() -> {
            try {
                client.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(client::acceptUserInput).start();
    }

}
