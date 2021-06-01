package ua.training.project.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.services.UserUpdateService;
import ua.training.project.model.services.validation.InputValidation;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.PRG_UPDATE_PROFILE;

/**
 * Command for user to change personal profile
 *
 * @author Iryna Radchuk
 * @see Command
 */
public class UserProfileChangePostCommand implements Command {
    private static final Logger log = LogManager.getLogger(UserProfileChangePostCommand.class);
    private ServletUtil servletUtil = new ServletUtil();
    private InputValidation validation = new InputValidation();

    @Override
    public String execute(HttpServletRequest request) {
        Integer id = servletUtil.getSessionID(request);
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");
        String firstName = request.getParameter("user_first_name");
        String lastName = request.getParameter("user_last_name");
        UserRegistrationDTO userDTO = new UserRegistrationDTO(email, password, firstName, lastName);
        if (!validation.inputValidation(userDTO).isEmpty()) {
            servletUtil.setErrorMessagesToSession(request, validation.inputValidation(userDTO));
            log.error(validation.inputValidation(userDTO));
            servletUtil.setPRGToSession(request, PRG_UPDATE_PROFILE);
            return REDIRECT + UPDATE;
        }
        UserUpdateService service = new UserUpdateService();
        service.userUpdate(userDTO, id);
        log.info(LoggerInfo.UPDATE_SUCCESS.getMessage());
        return REDIRECT + PROFILE;
    }


}
