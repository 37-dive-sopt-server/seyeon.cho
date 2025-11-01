package org.sopt.common;

import org.sopt.dto.response.ApiCode;

public record SuccessResponse<T>(
        String status,
        String code,
        String message,
        T data
) {
    public static <T> SuccessResponse<T> of(ApiCode apiCode, T data) {
        return new SuccessResponse<>(
                "SUCCESS",
                apiCode.code(),
                apiCode.message(),
                data
        );
    }

    public static <T> SuccessResponse<T> of(String message, T data) {
        return new SuccessResponse<>("SUCCESS", "s2000", message, data);
    }
}
