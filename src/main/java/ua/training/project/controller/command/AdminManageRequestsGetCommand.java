package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_REQUESTS_PAGE;

public class AdminManageRequestsGetCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return MANAGE_REQUESTS_PAGE;
    }
}
