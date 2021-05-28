package ua.training.project.exception;

public class PermissionDeniedException extends TimeTrackerException {

    public PermissionDeniedException(ExceptionMessage message) {
        super(message);
    }
}
