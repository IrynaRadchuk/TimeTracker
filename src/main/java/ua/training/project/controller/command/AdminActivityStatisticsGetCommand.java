package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.ACTIVITY_STAT_PAGE;

public class AdminActivityStatisticsGetCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ACTIVITY_STAT_PAGE;
    }
}
