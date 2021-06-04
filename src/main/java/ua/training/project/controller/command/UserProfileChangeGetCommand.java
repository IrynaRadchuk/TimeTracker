package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.Path.UPDATE_PAGE;
import static ua.training.project.constant.SessionCall.PRG_UPDATE_PROFILE;

/**
 * Command for user to see personal profile update page
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class UserProfileChangeGetCommand extends PRG implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_UPDATE_PROFILE)) {
            executePRG(request);
        }
        User user = userRepository.getUserFromDB(servletUtil.getSessionID(request));
        request.setAttribute(USERS_EMAIL, user.getEmail());
        request.setAttribute(USERS_PASSWORD, user.getPassword());
        request.setAttribute(USERS_FIRST_NAME, user.getFirstName());
        request.setAttribute(USERS_LAST_NAME, user.getLastName());
        return UPDATE_PAGE;
    }
}
