package ua.training.project.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.entity.Role;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.*;

public class LoginPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginPostCommand.class);
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.equals("") || password == null || password.equals("")) {
            request.setAttribute("error", ExceptionMessage.NOT_BLANK_CREDENTIALS);
            log.error(ExceptionMessage.NOT_BLANK_CREDENTIALS);
            return LOGIN_PAGE;
        }
        User user = userRepository.getUserFromDBByEmail(email);
        System.out.println(user.getId());
        if (user.getEmail() == null) {
            request.setAttribute("error", ExceptionMessage.USER_NOT_EXIST);
            log.error(ExceptionMessage.USER_NOT_EXIST);
            return LOGIN_PAGE;
        }
        if (!user.getPassword().equals(password)) {
            request.setAttribute("error", ExceptionMessage.WRONG_PASSWORD);
            log.error(ExceptionMessage.WRONG_PASSWORD);
            return LOGIN_PAGE;
        }
        servletUtil.setUserEmailRoleToSession(request, user.getRole(), user.getId());
        servletUtil.addToContext(request, user.getId());
        Role userRole = Role.getRole(user);
        if (userRole == Role.ADMIN) {
            log.info(LoggerInfo.LOGIN_SUCCESS);
            return LOGIN_ADMIN;
        }
        if (userRole == Role.USER) {
            log.info(LoggerInfo.LOGIN_SUCCESS);
            return LOGIN_USER;
        } else {
            request.setAttribute("error", ExceptionMessage.WRONG_ROLE);
            log.error(ExceptionMessage.WRONG_ROLE);
            return LOGIN_PAGE;
        }
    }
}
