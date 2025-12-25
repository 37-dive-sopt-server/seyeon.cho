package org.sopt.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.common.code.BaseCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus implements BaseCode {
    // 400 BAD_REQUEST
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "f4001", "잘못된 요청입니다."),
    VALIDATION_INVALID_ARTICLE_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "f4002", "잘못된 게시글 요청입니다."),

    // 404 NOT_FOUND
    NOT_FOUND_MEMBER_EXCEPTION(HttpStatus.NOT_FOUND, "f4041", "존재하지 않는 회원입니다."),
    NOT_FOUND_ARTICLE_EXCEPTION(HttpStatus.NOT_FOUND, "f4042", "존재하지 않는 게시글입니다."),

    // 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "f5000", "서버 내부 오류입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
