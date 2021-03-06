package ua.training.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.REGISTRATION_PAGE;
import static ua.training.project.constant.SessionCall.PRG_REGISTRATION;

/**
 * Command for guest to see registration page
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class RegistrationGetCommand extends PRG implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_REGISTRATION)) {
            executePRG(request);
        }
        return REGISTRATION_PAGE;
    }
}
