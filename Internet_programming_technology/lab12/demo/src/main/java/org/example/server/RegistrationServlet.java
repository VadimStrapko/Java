package org.example.server;

import db.DbLogins;
import db.DbUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name="registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", "");
        resp.sendRedirect("registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbUser dbUser = new DbUser();
        dbUser.login = req.getParameter("login");
        dbUser.password = req.getParameter("password");
        dbUser.role = "user";
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
                getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
            dbLogins.openConnection();
            dbLogins.addUser(dbUser);
            dbLogins.closeConnection();
            resp.sendRedirect("login");
        }
        catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            req.setAttribute("loginError", "пользователь уже есть");
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
        }
    }
}
