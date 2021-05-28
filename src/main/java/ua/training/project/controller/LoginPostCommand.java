package ua.training.project.controller;

import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.*;

public class LoginPostCommand implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        System.out.println(email);
        String password = request.getParameter("password");
        System.out.println(password);
        if (email == null || email.equals("") || password == null || password.equals("")) {
            return LOGIN_PAGE;
        }
        User user = userRepository.getUserFromDBByEmail(email);
        System.out.println(user.getId());
        if (user.getEmail() == null) {
            request.setAttribute("error", "User doesn't exist");
            System.out.println("User doesn't exist");
            return LOGIN_PAGE;
        }
        if (!user.getPassword().equals(password)) {
            request.setAttribute("error", "Wrong password");
            System.out.println("Wrong password");
            return LOGIN_PAGE;
        }
        servletUtil.setUserEmailRoleToSession(request, user.getRole(), user.getId());
        servletUtil.addToContext(request, user.getId());
        Role userRole = Role.getRole(user);
        if (userRole == Role.ADMIN)
            return LOGIN_ADMIN;
        if (userRole == Role.USER)
            return LOGIN_USER;
        else {
            request.setAttribute("error", "Unspecified role");
            System.out.println("Wrong role");
            return LOGIN_PAGE;
        }
    }
}
