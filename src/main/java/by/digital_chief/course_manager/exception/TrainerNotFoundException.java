package by.digital_chief.course_manager.exception;

public class TrainerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "No trainer found ";

    public TrainerNotFoundException() {
        super(MESSAGE);
    }

    public TrainerNotFoundException(String message) {
        super(MESSAGE + message);
    }
}
