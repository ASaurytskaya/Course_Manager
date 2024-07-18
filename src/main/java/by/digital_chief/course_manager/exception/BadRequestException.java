package by.digital_chief.course_manager.exception;

public class BadRequestException extends RuntimeException {
    public static final String MESSAGE = "BAD REQUEST ";

    public BadRequestException() {
        super(MESSAGE);
    }

    public BadRequestException(String message) {
        super(MESSAGE + message);
    }
}
