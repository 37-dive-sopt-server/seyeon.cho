package org.sopt.dto.response;

import org.sopt.domain.Article;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String memberName,
        String title,
        String content,
        String tag,
        LocalDateTime createdAt
) {
    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getId(),
                article.getMember().getName(),
                article.getTitle(),
                article.getContent(),
                article.getTag().name(),
                article.getCreatedAt()
        );
    }
}
