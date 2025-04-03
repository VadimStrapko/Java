package org.example.server;

import db.DbLogins;
import db.DbUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        DbUser dbUser = new DbUser();
        dbUser.login = req.getParameter("login");
        dbUser.password = req.getParameter("password");
        DbLogins dbLogins = new DbLogins();
        try {
            req.removeAttribute("loginError");
            req.removeAttribute("passwordError");
            if (dbUser.login.isBlank()) {
                req.setAttribute("loginError", "Поле логин должно быть заполнено");
            }
            if (dbUser.password.isBlank()) {
                req.setAttribute("passwordError", "Поле пароль должно быть заполнено");
            }
            if (req.getAttribute("loginError") != null || req.getAttribute("passwordError") != null) {
                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
            dbLogins.openConnection();
            if (dbLogins.getUser(dbUser)) {
                Cookie user = new Cookie("user", dbUser.login);
                user.setMaxAge(-1);
                resp.addCookie(user);
                resp.sendRedirect("welcome");
            }
            else {
                req.setAttribute("loginError", "Неправильно задан логин/пароль");
                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
        catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
