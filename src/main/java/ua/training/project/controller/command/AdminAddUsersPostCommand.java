package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_USERS;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_ADD_USER;

/**
 * Command for admin to add new users
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminAddUsersPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminAddUsersPostCommand.class);
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("add_user_email");
        String firstName = request.getParameter("add_user_first_name");
        String lastName = request.getParameter("add_user_last_name");
        String role = request.getParameter("all_roles");
        userRepository.addUser(email, firstName, lastName, role);
        servletUtil.setPRGToSession(request, PRG_ADD_USER);
        log.info(LoggerInfo.USER_ADD.getMessage());
        return REDIRECT + MANAGE_USERS;
    }
}
