package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.USER_PAGE;

public class PersonalAccountGetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_PAGE;
    }
}
