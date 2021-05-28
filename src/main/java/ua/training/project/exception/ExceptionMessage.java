package ua.training.project.exception;

public enum ExceptionMessage {
    DB_CONNECTION("Unable to connect to database"),
    NOT_AVAILABLE_ACTIVITY("Activity is not available for user"),
    OVERTIME ("Working time per day cannot exceed 8 hours"),
    ACTIVITY_ALREADY_APPROVED("Activity is already approved");

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
