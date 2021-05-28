package ua.training.project.controller;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.USER_PERSONAL_ACCOUNT;

public class PersonalAccountGetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_PERSONAL_ACCOUNT;
    }
}
