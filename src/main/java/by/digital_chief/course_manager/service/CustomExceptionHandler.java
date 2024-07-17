package by.digital_chief.course_manager.service;

import by.digital_chief.course_manager.exception.BadRequestException;
import by.digital_chief.course_manager.exception.CourseNotFoundException;
import by.digital_chief.course_manager.exception.TrainerNotFoundException;
import by.digital_chief.course_manager.service.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private static final String ERROR_TYPE = "error";

    @ExceptionHandler(TrainerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTrainerNotFoundException(TrainerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourseNotFoundException(CourseNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
