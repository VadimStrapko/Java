package org.lab9.lab9;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;


@WebFilter(filterName = "AuthFilter", urlPatterns = {"/Task3"})
public class InputFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String connectionString = "jdbc:sqlserver://localhost;database=JAVA_LAB9;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(connectionString, "sa", "1111")) {
            request.setCharacterEncoding("windows-1251");
            response.setContentType("text/html; charset=UTF-8");

            String userLogin = request.getParameter("userLogin");
            String userPassword = request.getParameter("userPassword");
            userPassword = Integer.toString(userPassword.hashCode());

            String sql = "SELECT typeOfUser FROM users WHERE username = ? AND password = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userLogin);
                statement.setString(2, userPassword);
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    // устанавливаем атрибут в сессию
                    HttpSession session = ((HttpServletRequest) request).getSession();
                    session.setAttribute("loggedIn", true);

                }
                else {
                    HttpSession session = ((HttpServletRequest) request).getSession();
                    session.setAttribute("loggedIn", false);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }
}