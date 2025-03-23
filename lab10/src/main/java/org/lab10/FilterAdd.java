package org.lab10;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;

@WebFilter(filterName = "FilterAdd", urlPatterns = {"/*"})
public class FilterAdd implements Filter {

    static {
        new DOMConfigurator().doConfigure("D:\\study\\4_sem\\JAVA\\lab10\\src\\main\\java\\org\\lab10\\log\\log4j.xml", LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(FilterAdd.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        MyData.isRegister = false;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURL().toString();

        if (url.equals("http://localhost:8080/lab10-1.0-SNAPSHOT/welcome.jsp") && !MyData.isRegister) {
            request.setAttribute("name", "Нет авторизации для выполнения данной команды");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }

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

            String sql = "SELECT id FROM Users WHERE login = ? AND password = ?";
            String userPassword1  = userPassword;
            if(userPassword != null) {
                userPassword = Integer.toString(userPassword.hashCode());
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userLogin);
                statement.setString(2, userPassword);
                ResultSet result = statement.executeQuery();

                if(userLogin != null && userPassword != null) {
                    if (result.next()) {
                        HttpSession session = ((HttpServletRequest) request).getSession();
                        session.setAttribute("loggedIn", true);
                        MyData.ThisID = result.getInt("id");
                        MyData.isRegister = true;
                        MyData.ThisName = userLogin;
                    } else {
                        HttpSession session = ((HttpServletRequest) request).getSession();
                        session.setAttribute("loggedIn", false);
                    }
                }
                chain.doFilter(request, response);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String servletPath = httpRequest.getServletPath();
        String method = httpRequest.getMethod();
        String timeStamp = LocalTime.now().toString();
        String remoteAddress = request.getRemoteAddr();

        LOG.info("Log: " +servletPath + method + timeStamp + remoteAddress);
    }
}