package ua.training.project.controller;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.LOGIN_ADMIN;

public class AdminStatisticsGetCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return LOGIN_ADMIN;
    }
}
