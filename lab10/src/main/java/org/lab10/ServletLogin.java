package org.lab10;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<MyData> myDataList = new ArrayList<MyData>();
        String userLogin = request.getParameter("userLogin");

        // устанавливаем атрибут в сессию
        HttpSession session = ((HttpServletRequest) request).getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(loggedIn != null && loggedIn)
        {
            response.setContentType("text/html;charset=UTF-8");
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

                Statement stmt = connection.createStatement();
                String sql2 = "select g.id, g.name, g.cost from \n" +
                        "Users u inner join Games g \n" +
                        "on u.id = g.userID\n" +
                        "where u.id = "+ Integer.toString(MyData.ThisID) + ";";

                try (ResultSet rs = stmt.executeQuery(sql2)) {
                    while (rs.next()) {
                        MyData myData = new MyData();
                        myData.setId(rs.getInt("id"));
                        myData.setName(rs.getString("name"));
                        myData.setCost(rs.getInt("cost"));
                        myDataList.add(myData);

                       /* information.add(Integer.toString(rs.getInt(1)) +"-----"+ rs.getString(2) +
                                "------" + Integer.toString(rs.getInt(3)) + "-------" + rs.getString(4));*/
                    }
                    rs.close();
                    stmt.close();
                    connection.close();


                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("myDataList", myDataList);
            request.setAttribute("name", MyData.ThisName);
            getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);

        }

        else
        {
            response.sendRedirect("index.jsp?error=true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}