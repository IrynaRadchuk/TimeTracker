package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.*;

public class AdminUserStatisticsPostCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return REDIRECT+USER_STAT;
    }
}
