package ua.training.project.exception;

public class TimeTrackerException extends RuntimeException {
    private int httpCode;
    private ExceptionMessage message;
    private String errorPagePath; //TODO: should be used to redirect user in case of error scenario

    public TimeTrackerException(int httpCode, ExceptionMessage message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message.getMessage();
    }

    public void setMessage(ExceptionMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Exception: " + httpCode + " " + message;
    }
}
