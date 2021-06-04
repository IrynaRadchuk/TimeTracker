package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.Path.PROFILE_PAGE;

/**
 * Command for user to see personal profile
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class UserProfileGetCommand implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        User user = userRepository.getUserFromDB(servletUtil.getSessionID(request));
        request.setAttribute(USERS_EMAIL, user.getEmail());
        request.setAttribute(USERS_PASSWORD, user.getPassword());
        request.setAttribute(USERS_FIRST_NAME, user.getFirstName());
        request.setAttribute(USERS_LAST_NAME, user.getLastName());
        return PROFILE_PAGE;
    }
}
