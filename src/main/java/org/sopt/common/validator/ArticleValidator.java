package org.sopt.common;

import org.sopt.domain.ArticleTag;

public class ArticleValidator {

    public static void validateInput(String title, String content, ArticleTag tag) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수 입력값입니다.");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수 입력값입니다.");
        }
        if (tag == null) {
            throw new IllegalArgumentException("태그는 필수 입력값입니다.");
        }
    }
}
