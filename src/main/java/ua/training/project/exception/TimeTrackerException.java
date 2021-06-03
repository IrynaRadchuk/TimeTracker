package ua.training.project.exception;

public class TimeTrackerException extends RuntimeException {
    private String url;
    private ExceptionMessage message;

    public TimeTrackerException(String url, ExceptionMessage message) {
        super(message.getMessage());
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
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
