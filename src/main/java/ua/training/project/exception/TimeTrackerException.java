package ua.training.project.exception;

public class TimeTrackerException extends RuntimeException {
    private ExceptionMessage message;

    public TimeTrackerException(ExceptionMessage message) {
        this.message = message;
    }


    public String getMessage() {
        return message.getMessage();
    }

    public void setMessage(ExceptionMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Exception: " + message;
    }
}
