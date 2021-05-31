package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.REGISTRATION_PAGE;

public class RegistrationGetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return REGISTRATION_PAGE;
    }
}
