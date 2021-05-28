package ua.training.project.controller;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.ACTIVITY_REQUEST_PAGE;

public class ActivityRequestPostCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return ACTIVITY_REQUEST_PAGE;
    }
}
