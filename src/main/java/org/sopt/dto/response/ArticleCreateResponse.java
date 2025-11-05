package org.sopt.dto.response;

import org.sopt.domain.Article;

import java.time.LocalDateTime;

public record ArticleCreateResponse(
        Long id,
        Long memberId,
        String title,
        String content,
        String tag,
        LocalDateTime createdAt
) {
    public static ArticleCreateResponse from(Article article) {
        return new ArticleCreateResponse(
                article.getId(),
                article.getMember().getId(),
                article.getTitle(),
                article.getContent(),
                article.getTag().name(),
                article.getCreatedAt()
        );
    }
}
