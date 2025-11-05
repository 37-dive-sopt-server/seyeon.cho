package org.sopt.service;

import org.sopt.domain.Article;
import org.sopt.domain.ArticleTag;
import org.sopt.domain.Member;
import org.sopt.dto.request.ArticleCreateRequest;
import org.sopt.dto.response.ArticleCreateResponse;
import org.sopt.dto.response.ArticleResponse;
import org.sopt.dto.response.ArticleListResponse;
import org.sopt.repository.ArticleRepository;
import org.sopt.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ArticleCreateResponse createArticle(ArticleCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (articleRepository.findByTitle(request.title()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 게시글 제목입니다.");
        }

        ArticleTag tag = ArticleTag.fromString(request.tag());

        Article article = new Article(member, request.title(), request.content(), tag);
        articleRepository.save(article);

        return ArticleCreateResponse.from(article);
    }

    @Override
    public ArticleResponse getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return ArticleResponse.from(article);
    }

    @Override
    public ArticleListResponse getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleResponse> responses = articles.stream()
                .map(ArticleResponse::from)
                .collect(Collectors.toList());

        return ArticleListResponse.from(responses);
    }
}
