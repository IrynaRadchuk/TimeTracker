package ua.training.project.controller.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.controller.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.*;


public class RegistrationPRGCommand implements Command {
    private static final Logger log = LogManager.getLogger(RegistrationPRGCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("PRGGGGGGGGGGGGGGGGGG REGISTRATION");

        ServletUtil servletUtil = new ServletUtil();
        String errorMessage = servletUtil.getErrorMessage(request);

        if (StringUtils.isNoneEmpty(errorMessage)) {
            request.setAttribute("error", errorMessage);
            return REGISTRATION_PAGE;
        }
        return REDIRECT + LOGIN;
    }
}
