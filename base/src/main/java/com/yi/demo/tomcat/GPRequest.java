package com.yi.demo.tomcat;

import java.io.InputStream;

public class GPRequest {

    private String method;
    private String url;

    public GPRequest(InputStream in) {
        try {
            String content = "";
            byte[] buf = new byte[1024];
            int len = 0;
            if ((len = in.read(buf)) > 0) {
                content = new String(buf, 0, len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getMethod() {
        return this.method;
    }

}
