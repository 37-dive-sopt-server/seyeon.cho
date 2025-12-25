package org.sopt.controller;

import org.sopt.common.SuccessResponse;
import org.sopt.controller.common.SuccessStatus;
import org.sopt.dto.request.ArticleCreateRequest;
import org.sopt.dto.response.ArticleCreateResponse;
import org.sopt.dto.response.ArticleListResponse;
import org.sopt.dto.response.ArticleResponse;
import org.sopt.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<ArticleCreateResponse>> createArticle(
            @RequestBody ArticleCreateRequest request
    ) {
        ArticleCreateResponse response = articleService.createArticle(request);
        return ResponseEntity
                .status(SuccessStatus.SUCCESS_CREATE_ARTICLE.getHttpStatus())
                .body(SuccessResponse.of(SuccessStatus.SUCCESS_CREATE_ARTICLE, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ArticleResponse>> getArticle(
            @PathVariable Long id
    ) {
        ArticleResponse response = articleService.getArticle(id);
        return ResponseEntity
                .status(SuccessStatus.SUCCESS_FIND_ARTICLE.getHttpStatus())
                .body(SuccessResponse.of(SuccessStatus.SUCCESS_FIND_ARTICLE, response));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<ArticleListResponse>> getAllArticles() {
        ArticleListResponse response = articleService.getAllArticles();
        return ResponseEntity
                .status(SuccessStatus.SUCCESS_FIND_ALL_ARTICLES.getHttpStatus())
                .body(SuccessResponse.of(SuccessStatus.SUCCESS_FIND_ALL_ARTICLES, response));
    }
}
