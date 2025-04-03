package org.example.server.command.commands;

import db.DbLogins;
import db.DbUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.command.Command;
import org.example.server.connection.ConnectionPool;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterCommand implements Command {
    private Connection connection;
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DbUser dbUser = new DbUser();
        dbUser.login = req.getParameter("login");
        dbUser.password = req.getParameter("password");
        dbUser.role = "user";
        connection = ConnectionPool.getInstance().getConnection();
        DbLogins dbLogins = new DbLogins();
        dbLogins.getConnection(connection);
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
                return new CommandResult("registration.jsp", false);
            }
            dbLogins.addUser(dbUser);
            req.getSession().setAttribute("Name", dbUser.login);
            return new CommandResult("welcome.jsp", true);
        }
        catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            req.setAttribute("loginError", "пользователь уже есть");
            return new CommandResult("registration.jsp", false);
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
