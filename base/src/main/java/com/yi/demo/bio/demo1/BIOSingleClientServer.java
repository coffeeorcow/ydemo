package com.yi.demo.bio.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BIOSingleClientServer {

    private final int port;
    public static ServerSocket ss = null;

    private final ThreadPoolExecutor executor =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(2, r -> new Thread(new ThreadGroup("client-thread"), r));

    public BIOSingleClientServer(int port) {
        this.port = port;
    }

    private static class ClientThread implements Runnable {

        private final Socket client;
        private String tag;

        public ClientThread(Socket client) {
            this.client = client;
            this.tag = client.getInetAddress().getHostName() + ":" + client.getPort();
        }

        @Override
        public void run() {
            try (InputStream in = client.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.printf("客户端 %s 发送消息：%s \n", this.tag, line);

                    if (line.equals("end")) {
                        client.close();
                        break;
                    } else if (line.equals("stop")) {
                        ss.close();
                        return;
                    }
                }
            } catch (IOException e) {
                System.err.printf("连接出错，错误原因：%s \n", e.getMessage());
            }
        }
    }

    public void run() throws IOException {
        ss = new ServerSocket(this.port);
        Socket client;
        while ((client = ss.accept()) != null) {
            executor.execute(new ClientThread(client));
        }
    }

    public static void main(String[] args) throws IOException {
        new BIOSingleClientServer(8080).run();
    }

}
