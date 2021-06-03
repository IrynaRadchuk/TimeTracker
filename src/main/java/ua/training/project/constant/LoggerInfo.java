package ua.training.project.constant;

public enum LoggerInfo {
    ACTIVITY_REQUESTED("Activity successfully requested. Status:pending."),
    ACTIVITY_TIME_ADD("Time for activity successfully added."),
    REGISTRATION_SUCCESS("User successfully registered"),
    LOGIN_SUCCESS("User successfully logged in"),
    UPDATE_SUCCESS("User profile successfully updated");

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LoggerInfo(String message) {
        this.message = message;
    }
}
