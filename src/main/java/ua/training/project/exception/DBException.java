package ua.training.project.exception;

public class DBException extends TimeTrackerException{
    public DBException(ExceptionMessage message) {
        super(message);
    }
}
