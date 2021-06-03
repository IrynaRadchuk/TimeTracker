package ua.training.project.exception;

public class PermissionDeniedException extends Exception {
    private ExceptionMessage message;

    public PermissionDeniedException(ExceptionMessage message) {
        super(message.getMessage());
        this.message = message;
    }

    public ExceptionMessage getExceptionMessage() {
        return message;
    }
}
