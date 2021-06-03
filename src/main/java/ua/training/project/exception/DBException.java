package ua.training.project.exception;

public class DBException extends TimeTrackerException {
    public DBException(String url, ExceptionMessage message) {
        super(url, message);
    }
}
