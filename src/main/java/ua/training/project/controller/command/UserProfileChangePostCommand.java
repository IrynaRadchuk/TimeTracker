package ua.training.project.controller.command;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.LoggerInfo;
import ua.training.project.controller.util.ServletUtil;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.entity.User;
import ua.training.project.model.repository.UserRepository;
import ua.training.project.model.service.UserUpdateService;
import ua.training.project.model.service.validation.InputValidation;

import javax.servlet.http.HttpServletRequest;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.Path.*;
import static ua.training.project.constant.SessionCall.PRG_LOGIN;
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
    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Integer id = servletUtil.getSessionID(request);
        String email = request.getParameter(USERS_EMAIL);
        String password = request.getParameter(USERS_PASSWORD);
        String passwordConfirm = request.getParameter(USERS_PASSWORD_CONFIRM);
        String firstName = request.getParameter(USERS_FIRST_NAME);
        String lastName = request.getParameter(USERS_LAST_NAME);
        UserRegistrationDTO userDTO = new UserRegistrationDTO(email, password, firstName, lastName);
        User user = userRepository.getUserFromDB(id);
        if (!user.getPassword().equals(DigestUtils.md5Hex(passwordConfirm))) {
            servletUtil.setErrorToSession(request, ExceptionMessage.WRONG_PASSWORD.getMessage());
            servletUtil.setPRGToSession(request, PRG_UPDATE_PROFILE);
            log.error(ExceptionMessage.WRONG_PASSWORD.getMessage());
            return REDIRECT + UPDATE;
        }
        if (!validation.updateValidation(userDTO).isEmpty()) {
            servletUtil.setErrorMessagesToSession(request, validation.updateValidation(userDTO));
            log.error(validation.updateValidation(userDTO));
            servletUtil.setPRGToSession(request, PRG_UPDATE_PROFILE);
            return REDIRECT + UPDATE;
        }
        UserUpdateService service = new UserUpdateService();
        service.userUpdate(userDTO, id);
        log.info(LoggerInfo.UPDATE_SUCCESS.getMessage());
        return REDIRECT + PROFILE;
    }


}
