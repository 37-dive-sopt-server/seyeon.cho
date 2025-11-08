package org.sopt.dto.response;

public enum ApiCode {
    // 회원 관련
    SUCCESS_CREATE_MEMBER(201, "s2010", "회원이 성공적으로 생성되었습니다."),
    SUCCESS_DELETE_MEMBER(200, "s2001", "회원이 성공적으로 삭제되었습니다."),
    SUCCESS_FIND_MEMBER(200, "s2002", "회원 조회를 성공하였습니다."),
    FAILURE_MEMBER_NOT_FOUND(404, "f4041", "존재하지 않는 회원입니다."),
    FAILURE_INVALID_REQUEST(400, "f4001", "잘못된 요청입니다."),

    // 게시글 관련
    SUCCESS_CREATE_ARTICLE(201, "s3010", "게시글이 성공적으로 생성되었습니다."),
    SUCCESS_FIND_ARTICLE(200, "s3002", "게시글 조회를 성공하였습니다."),
    SUCCESS_FIND_ALL_ARTICLES(200, "s3003", "전체 게시글 조회를 성공하였습니다."),
    FAILURE_ARTICLE_NOT_FOUND(404, "f4042", "존재하지 않는 게시글입니다."),
    FAILURE_INVALID_ARTICLE_REQUEST(400, "f4002", "잘못된 게시글 요청입니다.");

    private final int status;
    private final String code;
    private final String message;

    ApiCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int status() { return status; }
    public String code() { return code; }
    public String message() { return message; }
}
