package ua.training.project.controller.command;

import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static ua.training.project.constant.Path.MANAGE_USERS_PAGE;
import static ua.training.project.constant.SessionCall.*;

public class AdminManageUsersGetCommand extends PRG implements Command {
    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_ADD_USER) || checkPRG(request, PRG_DELETE_USER) || checkPRG(request, PRG_UPDATE_USER)) {
            executePRG(request);
        }
        List<User> users = userRepository.getAllUsers();
        request.setAttribute("all_users", users);
        List<Role> roles = users.stream().map(x -> x.getRole()).collect(Collectors.toList());
        request.setAttribute("all_roles", roles);
        return MANAGE_USERS_PAGE;
    }
}
