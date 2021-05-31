package ua.training.project.controller.command;

import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_USERS_PAGE;

public class AdminAddUsersPostCommand implements Command {
    UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("add_user_email");
        String firstName = request.getParameter("add_user_first_name");
        String lastName = request.getParameter("add_user_last_name");
        String role = request.getParameter("all_roles");
        userRepository.addUser(email, firstName, lastName, role);
        return MANAGE_USERS_PAGE;
    }
}
