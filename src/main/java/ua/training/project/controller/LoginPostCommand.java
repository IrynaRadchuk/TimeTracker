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
import static ua.training.project.constant.SessionCall.USER_EMAIL;
import static ua.training.project.constant.SessionCall.USER_ROLE;

public class LoginPostCommand implements Command {
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.equals("") || password == null || password.equals("")) {
            return LOGIN_PAGE;
        }
        User user = userRepository.getUserFromDB(email);
        if (user.getEmail() == null) {
            //TODO: change into output
            request.setAttribute("error", "User doesn't exist");
            System.out.println("User doesn't exist");
            return LOGIN_PAGE;
        }
        if (!user.getPassword().equals(password)) {
            //Todo change into output
            request.setAttribute("error", "Wrong password");
            System.out.println("Wrong password");
            return LOGIN_PAGE;
        }


//        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
//                .getAttribute(USER_EMAIL);
//        if (loggedUsers.stream().anyMatch(email::equals)) {
//            return ERROR_PAGE;
//        }
        servletUtil.setUserEmailRoleToSession(request, user.getRole(), user.getEmail());
        servletUtil.addToContext(request, email);

//        HttpSession session = request.getSession();
//        session.setAttribute(USER_EMAIL, user.getEmail());
//        ServletContext context = request.getServletContext();
//        context.setAttribute(USER_EMAIL, email);
//        session.setAttribute(USER_ROLE, userRole);
//        loggedUsers.add(email);
//        request.getSession().getServletContext()
//                .setAttribute(USER_EMAIL, loggedUsers);
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
