package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.ACTIVITY_STAT_PAGE;
import static ua.training.project.constant.Path.USER_STAT_PAGE;

public class AdminUserStatisticsGetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return USER_STAT_PAGE;
    }
}
