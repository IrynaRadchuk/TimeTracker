package ua.training.project.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.services.UserUpdateService;
import ua.training.project.model.services.validation.InputValidation;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Path.PROFILE_PAGE;
import static ua.training.project.constant.Path.UPDATE_PAGE;

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
            request.setAttribute("error", validation.inputValidation(userDTO));
            log.error(validation.inputValidation(userDTO));
            return UPDATE_PAGE;
        }
        UserUpdateService service = new UserUpdateService();
        service.userUpdate(userDTO, id);
        log.info(LoggerInfo.UPDATE_SUCCESS);
        return PROFILE_PAGE;
    }


}
