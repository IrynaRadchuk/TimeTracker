package ua.training.project.controller;

import ua.training.project.dbRepository.UserRepository;
import ua.training.project.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.AUTHORIZED_USER;

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
        if (user.getEmail()==null){
            System.out.println("User doesn't exist");
            return PAGE_LOGIN;
        }
        if (!user.getPassword().equals(password)){
            System.out.println("Wrong password");
            return PAGE_LOGIN;
        }
        if (user.getRoleId() == 2)
            return LOGIN_ADMIN;
        if (user.getRoleId() == 1)
            return LOGIN_USER;
        HttpSession session = request.getSession();
        session.setAttribute(AUTHORIZED_USER, user.getEmail());
        return PAGE_LOGIN;
    }
}
