package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static ua.training.project.constant.Path.LOGIN_PAGE;
import static ua.training.project.constant.SessionCall.PRG_LOGIN;

/**
 * Command for guest to see log in form
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class LoginGetCommand extends PRG implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_LOGIN)) {
            executePRG(request);
        }
        cleanUpSession(request);
        return LOGIN_PAGE;
    }

    /**
     * Clean user session if it is opened elsewhere
     */
    public void cleanUpSession(HttpServletRequest request) {
        if (Objects.nonNull(servletUtil.getSessionID(request))) {
            servletUtil.deleteUserFromContextAndSession(request);
        }
    }
}
