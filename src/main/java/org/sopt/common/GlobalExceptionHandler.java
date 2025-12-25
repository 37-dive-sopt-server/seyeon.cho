package org.sopt.common;

import org.sopt.exception.ErrorStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        ErrorStatus errorStatus = ErrorStatus.NOT_FOUND_MEMBER_EXCEPTION;

        return ResponseEntity
                .status(errorStatus.getHttpStatus())
                .body(ErrorResponse.of(errorStatus, e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException e) {
        ErrorStatus errorStatus = ErrorStatus.VALIDATION_REQUEST_MISSING_EXCEPTION;

        return ResponseEntity
                .status(errorStatus.getHttpStatus())
                .body(ErrorResponse.of(errorStatus, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorStatus errorStatus = ErrorStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(errorStatus.getHttpStatus())
                .body(ErrorResponse.of(errorStatus, e.getMessage()));
    }
}
