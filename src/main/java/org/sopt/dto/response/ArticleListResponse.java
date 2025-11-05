package org.sopt.dto.response;

import java.util.List;

public record ArticleListResponse(
        List<ArticleResponse> articles
) {
    public static ArticleListResponse from(List<ArticleResponse> articles) {
        return new ArticleListResponse(articles);
    }
}
