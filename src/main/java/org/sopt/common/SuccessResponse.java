package org.sopt.common;

import org.sopt.dto.response.ApiCode;

public record SuccessResponse<T>(
        int status,
        String code,
        String message,
        T data
) {
    public static <T> SuccessResponse<T> of(ApiCode apiCode, T data) {
        return new SuccessResponse<>(
                apiCode.status(),
                apiCode.code(),
                apiCode.message(),
                data
        );
    }
}
