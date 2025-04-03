package org.example.server.command.commands;

import db.DbItems;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.command.Command;
import org.example.server.connection.ConnectionPool;

import java.sql.Connection;

public class UpdateCommand implements Command {
    private Connection connection;
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            DbItems dbItems = new DbItems();
            dbItems.getConnection(connection);
            dbItems.updateItem(req.getParameter("name3"), req.getParameter("newName"), req.getParameter("newFounder"));
            ConnectionPool.getInstance().releaseConnection(connection);
            return new CommandResult("welcome.jsp", false);
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
