package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.service.UserService;
import ua.training.project.model.service.validation.InputValidation;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.PRG_REGISTRATION;

/**
 * Command for guest to register
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class RegistrationPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(RegistrationPostCommand.class);
    private InputValidation validation = new InputValidation();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        request.getContextPath();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        UserRegistrationDTO userDTO = new UserRegistrationDTO(email, password, firstName, lastName);
        if (!validation.fullValidation(userDTO).isEmpty()) {
            servletUtil.setErrorMessagesToSession(request, validation.fullValidation(userDTO));
            log.error(validation.fullValidation(userDTO));
            servletUtil.setPRGToSession(request, PRG_REGISTRATION);
            return REDIRECT + REGISTRATION;
        }
        UserService service = new UserService();
        service.userRegistration(userDTO);
        log.info(LoggerInfo.REGISTRATION_SUCCESS.getMessage());
        return REDIRECT + LOGIN;
    }
}
