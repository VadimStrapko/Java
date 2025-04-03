package org.example.server.command.commands;

import db.DbLogins;
import db.DbUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.command.Command;
import org.example.server.connection.ConnectionPool;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginCommand implements Command {
    private Connection connection;
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DbUser dbUser = new DbUser();
        dbUser.login = req.getParameter("login");
        dbUser.password = req.getParameter("password");
        connection = ConnectionPool.getInstance().getConnection();
        DbLogins dbLogins = new DbLogins();
        dbLogins.getConnection(connection);
        try {
            if (dbUser.login.isBlank()) {
                req.setAttribute("loginError", "Поле логин должно быть заполнено");
            }
            if (dbUser.password.isBlank()) {
                req.setAttribute("passwordError", "Поле пароль должно быть заполнено");
            }
            if (req.getAttribute("loginError") != null || req.getAttribute("passwordError") != null) {
                return new CommandResult("login.jsp", false);
            }
            if (dbLogins.getUser(dbUser)) {
                req.getSession().setAttribute("Name", dbUser.login);
                return new CommandResult("welcome.jsp", true);
            }
            else {
                req.setAttribute("loginError", "не найден пользователь");
                return new CommandResult("login.jsp", false);
            }
        }
        catch (SQLException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
