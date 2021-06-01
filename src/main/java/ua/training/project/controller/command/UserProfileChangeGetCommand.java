package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

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
        request.setAttribute("user_email", user.getEmail());
        request.setAttribute("user_password", user.getPassword());
        request.setAttribute("user_first_name", user.getFirstName());
        request.setAttribute("user_last_name", user.getLastName());
        return UPDATE_PAGE;
    }
}
