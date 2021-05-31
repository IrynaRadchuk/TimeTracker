package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.LOGIN_PAGE;


public class LoginGetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        cleanUpSession(request);
        return LOGIN_PAGE;
    }
    private void cleanUpSession(HttpServletRequest request) {
        ServletUtil servletUtil = new ServletUtil();
        if (servletUtil.getSessionID(request) != null) {
            servletUtil.deleteUserFromContextAndSession(request);
        }
    }
}
