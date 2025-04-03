package org.example.server.command.commands;

import org.example.server.command.Command;

public class CommandFactory {
    public static Command create(String command) {
        command = command.toUpperCase();
        System.out.println(command);
        CommandType commandType = CommandType.valueOf(command);
        Command resultCommand = null;
        switch (commandType) {
            case LOGIN:
                resultCommand = new LoginCommand();
                break;
            case TOLOGIN:
                resultCommand = new ToLoginCommand();
                break;
            case SIGN_OUT:
                resultCommand = new SignOutCommand();
                break;
            case REGISTER:
                resultCommand = new RegisterCommand();
                break;
            case CREATE:
                resultCommand = new CreateCommand();
                break;
            case UPDATE:
                resultCommand = new UpdateCommand();
                break;
            case DELETE:
                resultCommand = new DeleteCommand();
                break;
        }
        return resultCommand;
    }
}
