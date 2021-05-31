package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.services.UserService;
import ua.training.project.model.services.validation.InputValidation;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.*;

public class RegistrationPostCommand implements Command {
    private static final Logger log = LogManager.getLogger(RegistrationPostCommand.class);
    private InputValidation validation = new InputValidation();
    private ServletUtil servletUtil = new ServletUtil();

    @Override
    public String execute(HttpServletRequest request) {
        request.getContextPath();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserRegistrationDTO userDTO = new UserRegistrationDTO(email, password, firstName, lastName);
        if (!validation.inputValidation(userDTO).isEmpty()) {
            servletUtil.setErrorMessagesToSession(request, validation.inputValidation(userDTO));
            log.error(validation.inputValidation(userDTO));
            return REDIRECT + REGISTRATION_PRG;
        }
        UserService service = new UserService();
        service.userRegistration(userDTO);
        log.info(LoggerInfo.REGISTRATION_SUCCESS.getMessage());
        return REDIRECT + REGISTRATION_PRG;
    }
}
