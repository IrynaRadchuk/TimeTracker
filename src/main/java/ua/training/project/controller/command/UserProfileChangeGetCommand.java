package ua.training.project.controller.command;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserActivityRepository;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.UPDATE_PAGE;

public class UserProfileChangeGetCommand implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();
    private UserActivityRepository userActivityRepository = UserActivityRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        User user = userRepository.getUserFromDB(servletUtil.getSessionID(request));
        request.setAttribute("user_email", user.getEmail());
        request.setAttribute("user_password", user.getPassword());
        request.setAttribute("user_first_name", user.getFirstName());
        request.setAttribute("user_last_name", user.getLastName());
        return UPDATE_PAGE;
    }
}
