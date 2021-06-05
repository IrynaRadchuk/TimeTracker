package ua.training.project.model.service.validation;

import org.junit.Assert;
import org.junit.Test;
import ua.training.project.model.dto.UserRegistrationDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidationTest {

    @Test
    public void updateValidationEmptyEmailTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("", "Qwerty123", "John", "Snow");
        List<String> expected = new ArrayList<>(Arrays.asList("Email cannot be empty\n", "Wrong email format\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateValidationEmptyPasswordTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("snow@gmail.com", "", "John", "Snow");
        List<String> expected = new ArrayList<>(Arrays.asList("Password cannot be empty\n",
                "Wrong password format (Password must contain at least one digit [0-9],\n" +
                        "one lowercase Latin character [a-z],\n" +
                        "one uppercase Latin character [A-Z],\n" +
                        "password must contain from 6 to 20 digits)\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateValidationEmptyFirstNameTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("snow@gmail.com", "Qwerty123", "", "Snow");
        List<String> expected = new ArrayList<>(Arrays.asList("First name cannot be empty\n",
                "Wrong first name format (First name must be written in latin and start with capital letter)\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateValidationEmptyLastNameTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("snow@gmail.com", "Qwerty123", "John", "");
        List<String> expected = new ArrayList<>(Arrays.asList("Last name cannot be empty\n",
                "Wrong last name format (Last name must be written with latin letters and start with capital letter)\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateValidationWrongEmailTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("1", "Qwerty123", "John", "Snow");
        List<String> expected = new ArrayList<>(Arrays.asList("Wrong email format\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateValidationWrongPasswordTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("snow@gmail.com", "1", "John", "Snow");
        List<String> expected = new ArrayList<>(Arrays.asList("Wrong password format (Password must contain at least one digit [0-9],\n" +
                        "one lowercase Latin character [a-z],\n" +
                        "one uppercase Latin character [A-Z],\n" +
                        "password must contain from 6 to 20 digits)\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateValidationWrongFirstNameTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("snow@gmail.com", "Qwerty123", "1", "Snow");
        List<String> expected = new ArrayList<>(Arrays.asList("Wrong first name format (First name must be written in latin and start with capital letter)\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateValidationWrongLastNameTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("snow@gmail.com", "Qwerty123", "John", "1");
        List<String> expected = new ArrayList<>(Arrays.asList("Wrong last name format (Last name must be written with latin letters and start with capital letter)\n"));
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void updateValidationOkTest() {
        UserRegistrationDTO userDTO = new UserRegistrationDTO("snow@gmail.com", "Qwerty123", "John", "Snow");
        List<String> expected = new ArrayList<>();
        List<String> actual = new InputValidation().updateValidation(userDTO);
        Assert.assertEquals(expected, actual);
    }
}