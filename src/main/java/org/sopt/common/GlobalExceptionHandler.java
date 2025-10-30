package org.sopt.common;

import org.sopt.dto.response.ApiCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity
                .status(ApiCode.FAILURE_MEMBER_NOT_FOUND.status())
                .body(ErrorResponse.of(ApiCode.FAILURE_MEMBER_NOT_FOUND.status(), ApiCode.FAILURE_MEMBER_NOT_FOUND.code(), e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException e) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(ApiCode.FAILURE_INVALID_REQUEST.status(), ApiCode.FAILURE_INVALID_REQUEST.code(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.of(500, "f5000", e.getMessage()));
    }
}
