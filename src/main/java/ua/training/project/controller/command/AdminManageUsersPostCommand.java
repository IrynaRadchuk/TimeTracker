package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.Path.MANAGE_USERS;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_UPDATE_USER;

/**
 * Command for admin to change users
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminManageUsersPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminManageUsersPostCommand.class);
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(USERS_ID));
        String email = request.getParameter(USERS_EMAIL);
        String firstName = request.getParameter(USERS_FIRST_NAME);
        String lastName = request.getParameter(USERS_LAST_NAME);
        String role = request.getParameter(ALL_ROLES);
        userRepository.changeUser(email, firstName, lastName, role, id);
        servletUtil.setPRGToSession(request, PRG_UPDATE_USER);
        log.info(LoggerInfo.USER_UPDATED.getMessage());
        return REDIRECT + MANAGE_USERS;
    }
}
