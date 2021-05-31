package ua.training.project.controller.command;

import ua.training.project.constant.Path;
import ua.training.project.controller.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ServletUtil servletUtil = new ServletUtil();
        servletUtil.deleteUserFromContextAndSession(request);
        return Path.HOMEPAGE;
    }
}
