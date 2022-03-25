package se.iths.librarysystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({IdNotFoundException.class})
    public ResponseEntity<Object> idNotFoundException(IdNotFoundException exception) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(), exception.getPath()));
    }

    @ExceptionHandler({InvalidValueException.class})
    public ResponseEntity<Object> invalidValueException(InvalidValueException exception) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getPath()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException exception) {
        Map<String,String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage()));

        return new ResponseEntity<>(new ApiErrors(HttpStatus.BAD_REQUEST, errors, ""), HttpStatus.BAD_REQUEST);
    }

}