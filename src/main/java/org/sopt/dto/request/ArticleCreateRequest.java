package org.sopt.dto.request;

public record ArticleCreateRequest(
        Long memberId,   // 작성자 ID
        String title,    // 게시글 제목
        String content,  // 게시글 내용
        String tag       // 게시글 태그 (CS, DB, SPRING, ETC)
) {}
