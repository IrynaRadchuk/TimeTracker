package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Attributes.USERS_ID;
import static ua.training.project.constant.Path.MANAGE_USERS;
import static ua.training.project.constant.Path.REDIRECT;
import static ua.training.project.constant.SessionCall.PRG_DELETE_USER;

/**
 * Command for admin to delete users
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminDeleteUsersPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(AdminDeleteUsersPostCommand.class);
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(USERS_ID));
        userRepository.deleteUser(id);
        servletUtil.setPRGToSession(request, PRG_DELETE_USER);
        log.info(LoggerInfo.USER_DELETE.getMessage());
        return REDIRECT + MANAGE_USERS;
    }
}
