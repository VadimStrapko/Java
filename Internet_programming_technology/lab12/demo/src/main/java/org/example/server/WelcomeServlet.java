package org.example.server;

import db.DbItem;
import db.DbItems;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "welcome", value = "/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbItems dbItems = new DbItems();
        try {

            dbItems.openConnection();
            req.setAttribute("items", dbItems.getItems());
            dbItems.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/welcome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbItems dbItems = new DbItems();
        String type = req.getParameter("type");
        try {
            dbItems.openConnection();
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            if (type.equals("add")) {
                DbItem item = new DbItem();
                item.Name = req.getParameter("name");
                item.Founder = req.getParameter("founder");
                item.DateOfFound = new Date(new java.util.Date().getTime());
                dbItems.addItem(item);
            }
            if (type.equals("remove")) {
                dbItems.removeItem(req.getParameter("name2"));
            }
            if (type.equals("update")) {
                dbItems.updateItem(req.getParameter("name3"), req.getParameter("newName"), req.getParameter("newFounder"));
            }
            dbItems.closeConnection();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            dbItems.openConnection();
            req.setAttribute("items", dbItems.getItems());
            dbItems.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/welcome.jsp").forward(req, resp);
    }
}
