package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.ACTIVITY_STAT;
import static ua.training.project.constant.Path.REDIRECT;

public class AdminActivityStatisticsPostCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return REDIRECT+ACTIVITY_STAT;
    }
}
