package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.LOGIN_PRG;
import static ua.training.project.constant.Path.REDIRECT;

public class LoginPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginPostCommand.class);
    private UserRepository userRepository = UserRepository.getInstance();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            servletUtil.setErrorToSession(request, ExceptionMessage.NOT_BLANK_CREDENTIALS);
            log.error(ExceptionMessage.NOT_BLANK_CREDENTIALS.getMessage());
            return REDIRECT + LOGIN_PRG;
        }
        User user = userRepository.getUserFromDBByEmail(email);
        log.debug("userId = "+ user.getId());
        if (user.getEmail() == null) {
            servletUtil.setErrorToSession(request, ExceptionMessage.USER_NOT_EXIST);
            log.error(ExceptionMessage.USER_NOT_EXIST.getMessage());
            return REDIRECT + LOGIN_PRG;
        }
        if (!user.getPassword().equals(password)) {
            servletUtil.setErrorToSession(request, ExceptionMessage.WRONG_PASSWORD);
            log.error(ExceptionMessage.WRONG_PASSWORD.getMessage());
            return REDIRECT + LOGIN_PRG;
        }

        servletUtil.setUserToSession(request, user.getRole(), user.getId());

        return REDIRECT + LOGIN_PRG;

    }
}
