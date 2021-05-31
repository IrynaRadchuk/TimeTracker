package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
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
import static ua.training.project.constant.SessionCall.PRG_LOGIN;

public class LoginPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginPostCommand.class);
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            servletUtil.setErrorToSession(request, ExceptionMessage.NOT_BLANK_CREDENTIALS.getMessage());
            servletUtil.setPRGToSession(request, PRG_LOGIN);
            log.error(ExceptionMessage.NOT_BLANK_CREDENTIALS.getMessage());
            return REDIRECT + LOGIN;
        }
        User user = userRepository.getUserFromDBByEmail(email);
        log.debug("userId = " + user.getId());
        if (user.getEmail() == null) {
            servletUtil.setErrorToSession(request, ExceptionMessage.USER_NOT_EXIST.getMessage());
            servletUtil.setPRGToSession(request, PRG_LOGIN);
            log.error(ExceptionMessage.USER_NOT_EXIST.getMessage());
            return REDIRECT + LOGIN;
        }
        if (!user.getPassword().equals(password)) {
            servletUtil.setErrorToSession(request, ExceptionMessage.WRONG_PASSWORD.getMessage());
            servletUtil.setPRGToSession(request, PRG_LOGIN);
            log.error(ExceptionMessage.WRONG_PASSWORD.getMessage());
            return REDIRECT + LOGIN;
        }
        servletUtil.setUserToSession(request, user.getRole(), user.getId());
        servletUtil.setPRGToSession(request, PRG_LOGIN);
        log.info(LoggerInfo.LOGIN_SUCCESS.getMessage());
        log.debug("sessionRole= " + user.getRole());
        if (user.getRole() == Role.ADMIN) {
            return REDIRECT + MANAGE_USERS;
        }
        if (user.getRole() == Role.USER) {
            return REDIRECT + USER;
        }
        servletUtil.setErrorToSession(request, ExceptionMessage.WRONG_ROLE.getMessage());
        servletUtil.setPRGToSession(request, "login");
        log.error(ExceptionMessage.WRONG_ROLE.getMessage());
        return REDIRECT + LOGIN;
    }
}
