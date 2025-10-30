package org.sopt.common;

import org.springframework.http.HttpStatus;

public record SuccessResponse<T>(
        int status,
        String code,
        String message,
        T data
) {
    public static <T> SuccessResponse<T> of(HttpStatus status, String code, String message, T data) {
        return new SuccessResponse<>(status.value(), code, message, data);
    }

    public static <T> SuccessResponse<T> of(HttpStatus status, String code, String message) {
        return new SuccessResponse<>(status.value(), code, message, null);
    }
}
