package com.timemanagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        error.setErrors(Arrays.asList("Resource not found"));
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage("Validation failed");
        error.setErrors(errors);
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage("An unexpected error occurred");
        error.setErrors(Arrays.asList(ex.getMessage()));
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
