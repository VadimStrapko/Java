package org.lab9.lab9;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet4_2", value = "/Servlet4_2")
public class Servlet4_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("windows-1251");
        PrintWriter writer = response.getWriter();
        String p1 = (String) request.getAttribute("param1");
        String p2 = (String) request.getAttribute("param2");

        try {
            writer.println("<h2>Параметр: " + p1 + "</h2>");
            writer.println("<h2>Параметр: " + p2 + "</h2>");
        } finally {
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}