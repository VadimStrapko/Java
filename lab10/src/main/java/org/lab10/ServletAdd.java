package org.lab10;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

@WebServlet(name = "ServletAdd", value = "/ServletAdd")
public class ServletAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String connectionString = "jdbc:sqlserver://localhost;database=JAVA_LAB10;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(connectionString, "sa", "1111")) {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            String name = request.getParameter("name");
            int cost = Integer.parseInt(request.getParameter("cost"));
            Random random = new Random();
            int id = random.nextInt(1000 ) + 1;

            String sql2 = "INSERT INTO Games (id, name, cost, userID) VALUES" +
                    "(?, ?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(sql2)) {
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setInt(3, cost);
                statement.setInt(4, MyData.ThisID);
                int rowsAffected = statement.executeUpdate();


                if (rowsAffected > 0)
                {
                    //response.sendRedirect("index.jsp?error3=false");
                    ServletContext servletContext = getServletContext();
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/ServletLogin");
                    request.setAttribute("userLogin", MyData.ThisName);
                    requestDispatcher.forward(request, response);
                }

                else
                {
                    request.setAttribute("name", "Ошибка при добавлении");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage.jsp");
                    dispatcher.forward(request, response);
                }


            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}