package ua.training.project.controller;

import ua.training.project.model.repository.UserRepository;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashSet;
import java.util.List;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.USER_EMAIL;
import static ua.training.project.constant.SessionCall.USER_ROLE;

public class LoginCommand implements Command {
    UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.equals("") || password == null || password.equals("")) {
            return PAGE_LOGIN;
        }
        User user = userRepository.getUserFromDB(email);
        if (user.getEmail() == null) {
            //TODO: change into output
            request.setAttribute("error", "User doesn't exist");
            System.out.println("User doesn't exist");
            return PAGE_LOGIN;
        }
        if (!user.getPassword().equals(password)) {
            //Todo change into output
//            List<Student> students = studentService.getAllStudents();
//            request.setAttribute("students" , students);
            request.setAttribute("error", "Wrong password");
            System.out.println("Wrong password");
            return PAGE_LOGIN;
        }
        Role userRole = Role.getRole(user);
        if (userRole == Role.ADMIN)
            return LOGIN_ADMIN;
        if (userRole == Role.USER)
            return LOGIN_USER;
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute(USER_EMAIL);
        if (loggedUsers.stream().anyMatch(email::equals)) {
            return ERROR_PAGE;
        }
        HttpSession session = request.getSession();
        session.setAttribute(USER_EMAIL, user.getEmail());
        ServletContext context = request.getServletContext();
        context.setAttribute(USER_EMAIL, email);
        session.setAttribute(USER_ROLE, userRole);
        loggedUsers.add(email);
        request.getSession().getServletContext()
                .setAttribute(USER_EMAIL, loggedUsers);
        return PAGE_LOGIN;
    }
}
