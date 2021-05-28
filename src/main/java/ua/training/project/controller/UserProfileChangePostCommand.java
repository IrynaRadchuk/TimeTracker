package ua.training.project.controller;

import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.services.UserService;
import ua.training.project.model.services.UserUpdateService;
import ua.training.project.model.services.validation.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ua.training.project.constant.CredentialValidationRegex.*;
import static ua.training.project.constant.CredentialValidationRegex.NAME_REGEX_EN;
import static ua.training.project.constant.Path.*;

public class UserProfileChangePostCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");
        String firstName = request.getParameter("user_first_name");
        String lastName = request.getParameter("user_last_name");
        UserRegistrationDTO userDTO = new UserRegistrationDTO(email, password, firstName, lastName);
        if(!inputValidation(userDTO).isEmpty()){
            request.setAttribute("error", inputValidation(userDTO));
            return UPDATE_PAGE;
        }
        UserUpdateService service = new UserUpdateService();
        service.userUpdate(userDTO);
        return PROFILE_PAGE;
    }

    private List<String> inputValidation (UserRegistrationDTO userDTO) {
        List<String> errorMessages = new ArrayList<>();
        if (userDTO.getEmail().isEmpty()){
            errorMessages.add("Email cannot be empty\n");
        }
        if (userDTO.getPassword().isEmpty()){
            errorMessages.add("Password cannot be empty\n");
        }
        if (userDTO.getFirstName().isEmpty()){
            errorMessages.add("First name cannot be empty\n");
        }
        if (userDTO.getLastName().isEmpty()){
            errorMessages.add("Last name cannot be empty\n");
        }
        if(!ValidationUtil.validate(EMAIL_REGEX, userDTO.getEmail())){
            errorMessages.add("Wrong email form\n");
        }
        if(!ValidationUtil.validate(PASSWORD_REGEX, userDTO.getPassword())){
            errorMessages.add("Wrong password form (password must contain at least one digit [0-9],\n" +
                    "one lowercase Latin character [a-z],\n" +
                    "one uppercase Latin character [A-Z],\n" +
                    "password must contain from 6 to 20 digits)\n");
        }
        if(!ValidationUtil.validate(NAME_REGEX_EN, userDTO.getFirstName())){
            errorMessages.add("First name must be written in latin and start with capital letter\n");
        }
        if(!ValidationUtil.validate(NAME_REGEX_EN, userDTO.getLastName())){
            errorMessages.add("Last name must be written with latin letters and start with capital letter\n");
        }
        return errorMessages;
    }
}