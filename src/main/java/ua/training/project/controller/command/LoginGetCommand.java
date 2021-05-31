package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.LOGIN_PAGE;
import static ua.training.project.constant.SessionCall.PRG_LOGIN;


public class LoginGetCommand extends PRG implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        System.err.println("login HERE");
        if (checkPRG(request, PRG_LOGIN)) {
            System.err.println("login PRG HERE");
            executePRG(request);
        }
        cleanUpSession(request);
        return LOGIN_PAGE;
    }

    public void cleanUpSession(HttpServletRequest request) {
        if (servletUtil.getSessionID(request) != null) {
            servletUtil.deleteUserFromContextAndSession(request);
        }
    }
}
