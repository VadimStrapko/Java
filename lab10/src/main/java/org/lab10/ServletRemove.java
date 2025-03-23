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

@WebServlet(name = "ServletRemove", value = "/ServletRemove")
public class ServletRemove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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


            String sql2 = "delete from Games where Games.name = ?;";

            try (PreparedStatement statement = connection.prepareStatement(sql2)) {
                statement.setString(1, name);
                int rowsAffected = statement.executeUpdate();


                if (rowsAffected > 0)
                {
                    //response.sendRedirect("index.jsp?error4=false");
                    ServletContext servletContext = getServletContext();
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/ServletLogin");
                    request.setAttribute("userLogin", MyData.ThisName);
                    requestDispatcher.forward(request, response);
                }

                else
                {
                    request.setAttribute("name", "Нет такой игры для удаления");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}