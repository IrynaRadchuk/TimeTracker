package ua.training.project.exception;

public enum ExceptionMessage {
    DB_CONNECTION("Unable to connect to database"),
    NOT_AVAILABLE_ACTIVITY("Activity is not available for user"),
    OVERTIME("Working time per day cannot exceed 8 hours"),
    USER_NOT_EXIST("User doesn't exist"),
    WRONG_PASSWORD("Wrong password"),
    NOT_BLANK_CREDENTIALS("Email and password cannot be blank"),
    ACTIVITY_ALREADY_APPROVED("Activity is already approved"),
    WRONG_ROLE("Authorization cannot be completed. Role doesn't exist."),
    EMPTY_EMAIL("Email cannot be empty\n"),
    EMPTY_PASSWORD("Password cannot be empty\n"),
    EMPTY_FIRST_NAME("First name cannot be empty\n"),
    EMPTY_LAST_NAME("Last name cannot be empty\n"),
    EMAIL_MATCH("Wrong email format\n"),
    PASSWORD_MATCH("Wrong password format (Password must contain at least one digit [0-9],\n" +
            "one lowercase Latin character [a-z],\n" +
            "one uppercase Latin character [A-Z],\n" +
            "password must contain from 6 to 20 digits)\n"),
    FIRST_NAME_MATCH("Wrong first name format (First name must be written in latin and start with capital letter)\n"),
    LAST_NAME_MATCH("Wrong last name format (Last name must be written with latin letters and start with capital letter)\n");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
