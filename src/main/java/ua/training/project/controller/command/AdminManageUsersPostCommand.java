package ua.training.project.controller.command;

import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static ua.training.project.constant.Path.MANAGE_USERS_PAGE;

public class AdminManageUsersPostCommand implements Command {
    UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("user_id"));
        String email = request.getParameter("user_email");
        String firstName = request.getParameter("user_first_name");
        String lastName = request.getParameter("user_last_name");
        String role = request.getParameter("all_roles");
        userRepository.changeUser(email,firstName,lastName,role,id);
        return MANAGE_USERS_PAGE;
    }
}
