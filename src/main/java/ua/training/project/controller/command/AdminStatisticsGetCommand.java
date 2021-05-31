package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.ADMIN_PAGE;

public class AdminStatisticsGetCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return ADMIN_PAGE;
    }
}
