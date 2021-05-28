package ua.training.project.model.services.validation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.model.dto.UserRegistrationDTO;

import java.util.ArrayList;
import java.util.List;

import static ua.training.project.constant.CredentialValidationRegex.*;

public class InputValidation {
    private static final Logger log = LogManager.getLogger(InputValidation.class);

    public List<String> inputValidation(UserRegistrationDTO userDTO) {
        List<String> errorMessages = new ArrayList<>();
        if (userDTO.getEmail().isEmpty()) {
            errorMessages.add(ExceptionMessage.EMPTY_EMAIL.getMessage());
            log.error(ExceptionMessage.EMPTY_EMAIL);
        }
        if (userDTO.getPassword().isEmpty()) {
            errorMessages.add(ExceptionMessage.EMPTY_PASSWORD.getMessage());
            log.error(ExceptionMessage.EMPTY_PASSWORD);
        }
        if (userDTO.getFirstName().isEmpty()) {
            errorMessages.add(ExceptionMessage.EMPTY_FIRST_NAME.getMessage());
            log.error(ExceptionMessage.EMPTY_FIRST_NAME);
        }
        if (userDTO.getLastName().isEmpty()) {
            errorMessages.add(ExceptionMessage.EMPTY_LAST_NAME.getMessage());
            log.error(ExceptionMessage.EMPTY_LAST_NAME);
        }
        if (!ValidationUtil.validate(EMAIL_REGEX, userDTO.getEmail())) {
            errorMessages.add(ExceptionMessage.EMAIL_MATCH.getMessage());
            log.error(ExceptionMessage.EMAIL_MATCH);
        }
        if (!ValidationUtil.validate(PASSWORD_REGEX, userDTO.getPassword())) {
            errorMessages.add(ExceptionMessage.PASSWORD_MATCH.getMessage());
            log.error(ExceptionMessage.PASSWORD_MATCH);
        }
        if (!ValidationUtil.validate(NAME_REGEX_EN, userDTO.getFirstName())) {
            errorMessages.add(ExceptionMessage.FIRST_NAME_MATCH.getMessage());
            log.error(ExceptionMessage.FIRST_NAME_MATCH);
        }
        if (!ValidationUtil.validate(NAME_REGEX_EN, userDTO.getLastName())) {
            errorMessages.add(ExceptionMessage.LAST_NAME_MATCH.getMessage());
            log.error(ExceptionMessage.LAST_NAME_MATCH);
        }
        return errorMessages;
    }
}
