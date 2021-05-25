package ua.training.project.controller;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.LOGIN_PAGE;


public class LoginGetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return LOGIN_PAGE;
    }
}
