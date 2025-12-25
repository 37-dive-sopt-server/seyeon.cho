package org.sopt.common;

import org.sopt.exception.ErrorStatus;

public record ErrorResponse(
        String status,
        String code,
        String message
) {
    public static ErrorResponse of(ErrorStatus errorStatus) {
        return new ErrorResponse(
                "FAILURE",
                errorStatus.getCode(),
                errorStatus.getMessage()
        );
    }

    public static ErrorResponse of(ErrorStatus errorStatus, String message) {
        return new ErrorResponse(
                "FAILURE",
                errorStatus.getCode(),
                message
        );
    }
}
