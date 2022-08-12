package com.zensark.javaeedemo;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/home")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello from Java Servlet!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	PrintWriter writer = response.getWriter();
    	response.setContentType("text/html");
    	writer.println("<html>");
    	writer.println("<body>");
    	writer.println("<h1>Response from doPost</h1>");
    	writer.println("</body>");
    	writer.println("</html>");
    	writer.flush();
    }
    
    
    public void destroy() {
    }
}