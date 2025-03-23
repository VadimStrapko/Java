package org.lab9.lab9;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet(name = "Task3", value = "/Task3")
public class Servlet3 extends HttpServlet {

    public Servlet3(){
        super();
    }

@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {

        String connectionURL = "jdbc:sqlserver://localhost;database=JAVA_LAB9;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
        HttpSession session = ((HttpServletRequest) request).getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try(Connection connection = DriverManager.getConnection(connectionURL, "sa", "1111")) {
            response.setContentType("text/html");
            response.setCharacterEncoding("windows-1251");

            String action = request.getParameter("action");

            String userLogin = request.getParameter("userLogin");
            String userPassword = request.getParameter("userPassword");

            String sql = "SELECT typeOfUser FROM USERS WHERE username = ? AND password= ?";
            String userPassword1  = userPassword;
            userPassword = Integer.toString(userPassword.hashCode() );

            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userLogin);
                statement.setString(2, userPassword);
                ResultSet resultSet = statement.executeQuery();
                System.out.println(resultSet);
                if(loggedIn!= null && loggedIn  && "login".equals(action))
                {

                    LocalTime time = LocalTime.now();
                    LocalDate date = LocalDate.now();
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Результат отправки формы входа</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Добро пожаловать!</h1>");
                    out.println("<p>Ваш логин: " + userLogin + "</p>");
                    out.println("<p>Ваш пароль: " + userPassword1 + "</p>");
                    if(resultSet.next()) {
                        out.println("<p>Ваша роль: " + resultSet.getString(1) + "</p>"); //!!!!!!!!!!!!!!!!!!!!
                    }
                    out.println("<p>Текущее время: " + time + "</p>");
                    out.println("<p>Текущее дата: " + date + "</p>");
                    out.println("</body>");
                    out.println("</html>");

                    Cookie lastVisitCookie = getCookie(request, "lastVisit"+userLogin+userPassword);

                    LocalTime time2 = LocalTime.now();
                    String currentDateTime = time2.toString();

                    int visitCount = 1;
                    Cookie visitCountCookie = getCookie(request, "visitCount"+userLogin+userPassword);
                    if (visitCountCookie != null) {
                        try {
                            visitCount = Integer.parseInt(visitCountCookie.getValue());
                            visitCount++;
                        } catch (NumberFormatException e) {

                        }
                    }
                    // Получаем тип пользователя
                    String userType = resultSet.getString("typeOfUser");

                    // Создаем Cookie для даты последнего посещения
                    Cookie newLastVisitCookie = new Cookie("lastVisit"+userLogin+userPassword, currentDateTime);
                    newLastVisitCookie.setMaxAge(150 * 60 * 60); // время жизни Cookie - 150 часов

                    // Создаем Cookie для количества посещений
                    Cookie newVisitCountCookie = new Cookie("visitCount"+userLogin+userPassword, String.valueOf(visitCount));
                    newVisitCountCookie.setMaxAge(150 * 60 * 60);

                    // Создаем Cookie для типа пользователя
                    Cookie newTypeOfUserCookie = new Cookie("userType", userType);
                    newTypeOfUserCookie.setMaxAge(150 * 60 * 60);

                    // Добавляем созданные Cookie в объект HttpServletResponse
                    response.addCookie(newLastVisitCookie);
                    response.addCookie(newVisitCountCookie);
                    response.addCookie(newTypeOfUserCookie);

                    // Выводим результат пользователю
                    response.setContentType("text/html");

                    out.println("<h1>Привет, пользователь!</h1>");
                    if (lastVisitCookie != null) {
                        out.println("<p>Ваш последний визит был " + lastVisitCookie.getValue() + "</p>");
                    }
                    out.println("<p>Вы посетили эту страницу " + visitCount + " раз</p>");
                    out.println("</body></html>");
                }
                else if("register".equals(action))
                {
                    PrintWriter out = response.getWriter();
                   String sql1 = "SELECT * FROM USERS WHERE username = ? ";
                    try(PreparedStatement statement1 = connection.prepareStatement(sql1)) {
                        statement1.setString(1, userLogin);
                        ResultSet res = statement1.executeQuery();
                        if(res.next())
                        {
                            out.println("<h1>Пользователь c таким логином уже существует!!</h1>");
                            out.println("<a href = 't2.jsp'>Вернуться обратно</a>");
                        }
                        else{
                            String sql2 = "insert into USERS(username, password, typeOfUser)\n" +
                                    "values\n" +
                                    "(?, ?, 'user');";
                            try(PreparedStatement statement2 = connection.prepareStatement(sql2))
                            {
                                statement2.setString(1,userLogin);
                                statement2.setString(2,userPassword);
                                int rowsAffected = statement2.executeUpdate();

                                if (rowsAffected > 0) {
                                    out.println("<h1>Пользователь успешно прошёл регистрацию!</h1>");
                                    out.println("<a href = 't2.jsp'>Вернуться обратно</a>");
                                }
                            }
                        }
                    }

                }
                else if(loggedIn != null && !loggedIn && "login".equals(action))
                {
                    ((HttpServletResponse) response).sendRedirect("t5.jsp");
                }
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        } catch (IOException e) {
                throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req,res);
        System.out.println("service");
    }

    public void destroy(ServletRequest req, ServletResponse res)throws ServletException, IOException {
        super.destroy();
        System.out.println("destroy");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init");
    }

    // Метод для получения объекта Cookie по имени из запроса
    private Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
            return null;
        }
        return null;
    }
}
