package com.yi.demo.tomcat;

import com.google.common.collect.Maps;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;

public class GPTomcat {

    public static void main(String[] args) {
        new GPTomcat().start();
    }

    private int port = 8080;
    private ServerSocket server;
    private Map<String, GPServlet> servletMapping = Maps.newHashMap();
    private Properties webConfig = new Properties();

    public void start() {
        init();
        try {
            server = new ServerSocket(this.port);
            System.out.println("服务器已启动");
            while (true) {
                Socket client = server.accept();
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        // 读取 web.properties 配置文件
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            InputStream in = new FileInputStream(WEB_INF + "web.properties");
            webConfig.load(in);

            for (Object k : webConfig.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webConfig.getProperty(key);
                    String className = webConfig.getProperty(servletName + ".className");

                    GPServlet servlet = (GPServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, servlet);
                }
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void process(Socket client) throws Exception {
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        GPRequest req = new GPRequest(in);
        GPResponse resp = new GPResponse(out);

        String url = req.getUrl();
        if (servletMapping.containsKey(url)) {
            servletMapping.get(url).service(req, resp);
        } else {
            resp.write("404 - NOT FOUND");
        }

        out.flush();
        out.close();

        in.close();
        client.close();
    }

}
