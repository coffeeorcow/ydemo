package com.yi.demo.tomcat;

public class FirstServlet extends GPServlet {
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is First Servlet.");
    }
}
