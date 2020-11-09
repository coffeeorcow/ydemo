package com.yi.demo.bio.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket socket;
        while ((socket = ss.accept()) != null) {
            try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (Objects.equals("@end", line)) {
                        out.write("服务收到指令 - 结束对话".getBytes(StandardCharsets.UTF_8));
                        out.flush();
                        break;
                    } else if (Objects.equals("@close", line)) {
                        out.write("服务收到指令 - 关闭服务器".getBytes(StandardCharsets.UTF_8));
                        out.flush();
                        return;
                    }
                    System.out.println(line);
                    out.write(("服务收到消息 => " + line + "\r\n").getBytes(StandardCharsets.UTF_8));
                    out.flush();
                }
            } catch (Exception e) {
                System.err.println("出现异常：" + e.getMessage());
            }
        }
    }

}
