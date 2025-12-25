package org.sopt.controller.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.common.code.BaseCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus implements BaseCode {
    // 200 OK
    SUCCESS_FIND_MEMBER(HttpStatus.OK, "s2002", "회원 조회를 성공하였습니다."),
    SUCCESS_FIND_ARTICLE(HttpStatus.OK, "s3002", "게시글 조회를 성공하였습니다."),
    SUCCESS_FIND_ALL_ARTICLES(HttpStatus.OK, "s3003", "전체 게시글 조회를 성공하였습니다."),
    SUCCESS_UPDATE_COMMENT(HttpStatus.OK, "s2000", "댓글 수정 성공"),
    SUCCESS_DELETE_COMMENT(HttpStatus.OK, "s2000", "댓글 삭제 성공"),
    SUCCESS_FIND_COMMENT(HttpStatus.OK, "s2000", "댓글 조회 성공"),
    SUCCESS_DELETE_MEMBER(HttpStatus.OK, "s2001", "회원이 성공적으로 삭제되었습니다."),

    // 201 CREATED
    SUCCESS_CREATE_MEMBER(HttpStatus.CREATED, "s2010", "회원이 성공적으로 생성되었습니다."),
    SUCCESS_CREATE_ARTICLE(HttpStatus.CREATED, "s3010", "게시글이 성공적으로 생성되었습니다."),
    SUCCESS_CREATE_COMMENT(HttpStatus.CREATED, "s2010", "댓글 생성 성공"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
