package ua.training.project.controller;

import ua.training.project.constant.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        return Path.PAGE_LOGIN;
    }
}
