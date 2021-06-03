package ua.training.project.constant;

public enum LoggerInfo {
    ACTIVITY_REQUESTED("Activity successfully requested. Status:pending."),
    ACTIVITY_TIME_ADD("Time for activity successfully added."),
    ACTIVITY_ADD("Activity successfully added."),
    ACTIVITY_DELETED("Time for activity successfully deleted."),
    REGISTRATION_SUCCESS("User successfully registered."),
    LOGIN_SUCCESS("User successfully logged in."),
    UPDATE_SUCCESS("User profile successfully updated."),
    USER_ADD("User successfully added."),
    ACTIVITY_APPROVED("Activity successfully approved."),
    ACTIVITY_DELETE("Activity successfully deleted."),
    USER_DELETE("User successfully deleted."),
    REQUEST_DENY("Request successfully denied"),
    ACTIVITY_UPDATED("Activity successfully updated."),
    USER_UPDATED("User successfully updated."),
    LOGOUT("User successfully logged out.");

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
