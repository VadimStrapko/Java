package org.example.server.command.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.command.Command;

public class SignOutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("Name");
        return new CommandResult("registration.jsp", true);
    }
}
