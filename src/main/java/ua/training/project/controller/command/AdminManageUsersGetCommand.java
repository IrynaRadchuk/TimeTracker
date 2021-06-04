package ua.training.project.controller.command;

import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static ua.training.project.constant.Attributes.ALL_ROLES;
import static ua.training.project.constant.Attributes.ALL_USERS;
import static ua.training.project.constant.Path.MANAGE_USERS_PAGE;
import static ua.training.project.constant.SessionCall.*;

/**
 * Command for admin to see users list
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class AdminManageUsersGetCommand extends PRG implements Command {
    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkPRG(request, PRG_ADD_USER)
                || checkPRG(request, PRG_DELETE_USER)
                || checkPRG(request, PRG_UPDATE_USER)) {
            executePRG(request);
        }
        List<User> users = userRepository.getAllUsers();
        request.setAttribute(ALL_USERS, users);
        List<Role> roles = users.stream().map(x -> x.getRole()).distinct().collect(Collectors.toList());
        request.setAttribute(ALL_ROLES, roles);
        return MANAGE_USERS_PAGE;
    }
}
