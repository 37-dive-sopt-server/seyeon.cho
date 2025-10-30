package org.sopt.common;

public record ErrorResponse(
        String status,
        String code,
        String message
) {
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse("FAILURE", code, message);
    }
}
