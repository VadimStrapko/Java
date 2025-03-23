package org.example.server.command.commands;

import db.DbItem;
import db.DbItems;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.command.Command;
import org.example.server.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;

public class CreateCommand implements Command {
    private Connection connection;
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DbItem dbItem = new DbItem();
        dbItem.Name = req.getParameter("name");
        dbItem.Founder = req.getParameter("founder");
        dbItem.DateOfFound = new Date(new java.util.Date().getTime());
        DbItems dbItems = new DbItems();
        connection = ConnectionPool.getInstance().getConnection();
        try {
            dbItems.getConnection(connection);
            dbItems.addItem(dbItem);
            ConnectionPool.getInstance().releaseConnection(connection);
            return new CommandResult("welcome.jsp", false);
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
