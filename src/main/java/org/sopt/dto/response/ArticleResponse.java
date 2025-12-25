package org.sopt.dto.response;

import org.sopt.domain.Article;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponse(
        Long id,
        String memberName,
        String title,
        String content,
        String tag,
        LocalDateTime createdAt,
        List<CommentResponse> comments
) {
    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getId(),
                article.getMember().getName(),
                article.getTitle(),
                article.getContent(),
                article.getTag().name(),
                article.getCreatedAt(),
                article.getComments().stream()
                        .map(CommentResponse::from)
                        .toList()
        );
    }
}
