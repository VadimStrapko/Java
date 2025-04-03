package org.lab8.lab8glassfish;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Servletik", value = "/Servletik")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Double height = Double.valueOf(request.getParameter("height"));
            Double weight = Double.valueOf(request.getParameter("weight"));

            double ikt = weight / height / height;
            String message;

            if (ikt >= 19.5 && ikt <= 22.9)
                message = "Норма";
            else if (ikt >= 23.0 && ikt <= 27.4)
                message = "Избыток массы тела";
            else if (ikt >= 27.5 && ikt <= 29.9)
                message = "Ожирение 1 степени";
            else if (ikt >= 30.0 && ikt <= 34.9)
                message = "Ожирение 2 степени";
            else if (ikt < 19.5)
                message = "Очень худой";
            else
                message = "Сверхожирение";

            response.setContentType("text/html");
            response.setCharacterEncoding("windows-1251");

            PrintWriter out = response.getWriter();
            if (ikt > 0) {
                out.println("<html><body>");
                out.println("<h1>" + "Ваш ИКТ " + ikt + "</h1>");
                out.println("<p>" + "Классификация здоровья: " + message + "</p>");
                out.println("</body></html>");
            } else {
                response.setContentType("text/html");
                response.setCharacterEncoding("windows-1251");
                out.println("<html><body>");
                out.println("<h1>" + "Введенные данные некоректны!" + "</h1>");
                out.println("</body></html>");
            }
        }
        catch (NumberFormatException e)
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            response.setCharacterEncoding("windows-1251");
            out.println("<html><body>");
            out.println("<h1>" + "Введенные данные некоректны!" + "</h1>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}