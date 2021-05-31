package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.*;


public class LoginPRGCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginPRGCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ServletUtil servletUtil = new ServletUtil();
        String errorMessage = servletUtil.getErrorMessage(request);

        if (StringUtils.isNoneEmpty(errorMessage)){
            request.setAttribute("error", errorMessage);
            return LOGIN_PAGE;
        }

        Role sessionRole = servletUtil.getSessionRole(request);
        log.debug("sessionRole= " + sessionRole);
        if (sessionRole == Role.ADMIN) {
            log.info(LoggerInfo.LOGIN_SUCCESS.getMessage());
            return REDIRECT + USER;
        }
        if (sessionRole == Role.USER) {
            log.info(LoggerInfo.LOGIN_SUCCESS.getMessage());
            return REDIRECT + USER;
        } else {
            request.setAttribute("error", ExceptionMessage.WRONG_ROLE.getMessage());
            log.error(ExceptionMessage.WRONG_ROLE.getMessage());
            return LOGIN_PAGE;
        }
    }
}
