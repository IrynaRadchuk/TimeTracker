package ua.training.project.controller;

import ua.training.project.model.dto.UserRegistrationDTO;
import ua.training.project.model.services.UserService;
import ua.training.project.model.services.validation.ValidationUtil;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static ua.training.project.constant.CredentialValidationRegex.*;
import static ua.training.project.constant.Path.*;

public class RegistrationPostCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserRegistrationDTO userDTO = new UserRegistrationDTO(email, password, firstName, lastName);
        if(!inputValidation(userDTO).isEmpty()){
            return ERROR_PAGE;
        }
        UserService service = new UserService();
        service.userRegistration(userDTO);
        return HOMEPAGE;
    }

    private List<String> inputValidation (UserRegistrationDTO userDTO) {
        List<String> errorMessages = new ArrayList<>();
        if (userDTO.getEmail().isEmpty()){
            errorMessages.add("Email cannot be empty");
        }
        if (userDTO.getPassword().isEmpty()){
            errorMessages.add("Password cannot be empty");
        }
        if (userDTO.getFirstName().isEmpty()){
            errorMessages.add("First name cannot be empty");
        }
        if (userDTO.getLastName().isEmpty()){
            errorMessages.add("Last name cannot be empty");
        }
        if(!ValidationUtil.validate(EMAIL_REGEX, userDTO.getEmail())){
            System.out.println("EMAILREG");
            errorMessages.add("Wrong email form");
        }
        if(!ValidationUtil.validate(PASSWORD_REGEX, userDTO.getPassword())){
            System.out.println("PAssReg");
            errorMessages.add("Password must contain at least one digit [0-9].\n" +
                    "Password must contain at least one lowercase Latin character [a-z].\n" +
                    "Password must contain at least one uppercase Latin character [A-Z]." +
                    "Password must contain from 6 to 20 digits.");
        }
        if(!ValidationUtil.validate(NAME_REGEX_EN, userDTO.getFirstName())){
            System.out.println("NAMEReg");
            errorMessages.add("Name must be written in latin and start with capital letter");
        }
        if(!ValidationUtil.validate(NAME_REGEX_EN, userDTO.getLastName())){
            System.out.println("LASTReg");
            errorMessages.add("Surname must be written with latin letters and start with capital letter");
        }
        return errorMessages;
    }
}