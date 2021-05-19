package ua.training.project.exception;

public enum ExceptionMessage {
    DB_CONNECTION("Unable to connect to database");

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
