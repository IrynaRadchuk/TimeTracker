package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.constant.Path;
import ua.training.project.controller.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for user and admin to log out
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class LogoutCommand implements Command {
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ServletUtil servletUtil = new ServletUtil();
        servletUtil.deleteUserFromContextAndSession(request);
        log.info(LoggerInfo.LOGOUT.getMessage());
        return Path.HOMEPAGE;
    }
}
