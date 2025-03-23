package org.lab10;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Random;

@WebServlet(name = "ServletRegister", value = "/ServletRegister")
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // устанавливаем атрибут в сессию
        HttpSession session = ((HttpServletRequest) request).getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(loggedIn != null && !loggedIn)
        {
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

                String userLogin = request.getParameter("userLogin");
                String userPassword = request.getParameter("userPassword");
                Random random = new Random();
                int id = random.nextInt(1000000) + 1;

                String sql2 = "insert into Users(id, login, password)\n" +
                        "values\n" +
                        "(?, ?, ?);";

                String userPassword1  = userPassword;
                userPassword = Integer.toString(userPassword.hashCode() );

                try (PreparedStatement statement = connection.prepareStatement(sql2)) {
                    statement.setInt(1, id);
                    statement.setString(2, userLogin);
                    statement.setString(3, userPassword);
                    int rowsAffected = statement.executeUpdate();


                    if (rowsAffected > 0) {
                        response.sendRedirect("register.jsp?error2=true");
                    }

                    else if(rowsAffected == 0) {
                        response.sendRedirect("register.jsp?error2=false");
                        ((HttpServletResponse) response).sendRedirect("index.jsp");
                    }


                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        else if(loggedIn != null && loggedIn)
        {
            response.sendRedirect("register.jsp?error2=true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}