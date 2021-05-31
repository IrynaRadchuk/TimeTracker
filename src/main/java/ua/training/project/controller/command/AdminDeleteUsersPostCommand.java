package ua.training.project.controller.command;

import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.MANAGE_USERS_PAGE;

public class AdminDeleteUsersPostCommand implements Command {
    UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("user_id"));
        userRepository.deleteUser(id);
        return MANAGE_USERS_PAGE;
    }
}
