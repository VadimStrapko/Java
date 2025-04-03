package org.example.server;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.command.Command;
import org.example.server.command.commands.CommandFactory;
import org.example.server.command.commands.CommandResult;

import java.io.IOException;
import java.util.logging.Logger;

public class Controller extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "error_message";
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String command = req.getParameter(COMMAND);
        Command action = CommandFactory.create(command);
        LOGGER.info(COMMAND + "= " + command);
        CommandResult commandResult;
        commandResult = action.execute(req, resp);
        if (commandResult.isRedirect()) {
            resp.sendRedirect(commandResult.getPage());
        }
        else
        {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/" + commandResult.getPage());
            requestDispatcher.forward(req, resp);
        }
    }
}
