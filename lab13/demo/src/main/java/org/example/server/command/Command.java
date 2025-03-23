package org.example.server.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.command.commands.CommandResult;

public interface Command {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
}
