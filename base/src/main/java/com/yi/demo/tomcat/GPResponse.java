package com.yi.demo.tomcat;

import java.io.OutputStream;

public class GPResponse {

    private OutputStream out;

    public GPResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception {
        String sb = "HTTP/1.1 200 OK\n" +
                "Content-Text: text/html;\n" +
                "\r\n" +
                s;
        out.write(sb.getBytes());
    }
}
