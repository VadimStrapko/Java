package org.example.server.command.commands;

public enum CommandType {
    LOGIN("login"),
    TOLOGIN("toLogin"),
    SIGN_OUT("sign_out"),
    WELCOME("welcome"),
    REGISTER("register"),
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");
    private String command;
    private CommandType(String command) {
        this.command = command;
    }
}
