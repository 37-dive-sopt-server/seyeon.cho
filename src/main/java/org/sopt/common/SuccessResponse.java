package org.sopt.common;

import org.sopt.common.code.BaseCode;
import org.sopt.controller.common.SuccessStatus;

public record SuccessResponse<T>(
        String status,
        String code,
        String message,
        T data
) {
    public static <T> SuccessResponse<T> of(SuccessStatus successStatus, T data) {
        return new SuccessResponse<>(
                "SUCCESS",
                successStatus.getCode(),
                successStatus.getMessage(),
                data
        );
    }

    public static <T> SuccessResponse<T> of(BaseCode code, T data) {
        return new SuccessResponse<>(
                "SUCCESS",
                code.getCode(),
                code.getMessage(),
                data
        );
    }
}
