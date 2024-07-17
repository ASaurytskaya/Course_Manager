package by.digital_chief.course_manager.exception;

public class CourseNotFoundException extends RuntimeException {

    private static final String MESSAGE = "No course found ";

    public CourseNotFoundException() {
        super(MESSAGE);
    }

    public CourseNotFoundException(String message) {
        super(MESSAGE + message);
    }
}
