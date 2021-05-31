package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_REQUESTS;
import static ua.training.project.constant.Path.REDIRECT;

public class AdminManageRequestsPostCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return REDIRECT + MANAGE_REQUESTS;
    }
}
