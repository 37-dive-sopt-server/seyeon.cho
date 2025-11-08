package org.sopt.service;

import org.sopt.dto.request.ArticleCreateRequest;
import org.sopt.dto.response.ArticleCreateResponse;
import org.sopt.dto.response.ArticleResponse;
import org.sopt.dto.response.ArticleListResponse;

public interface ArticleService {

    // 게시글 생성
    ArticleCreateResponse createArticle(ArticleCreateRequest request);

    // 단일 게시글 조회
    ArticleResponse getArticle(Long articleId);

    // 전체 게시글 조회
    ArticleListResponse getAllArticles();
}
