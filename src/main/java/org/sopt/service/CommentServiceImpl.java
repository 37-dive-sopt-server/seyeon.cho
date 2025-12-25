package org.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.common.validator.CommentValidator;
import org.sopt.domain.Article;
import org.sopt.domain.Comment;
import org.sopt.domain.Member;
import org.sopt.dto.request.CommentRequest;
import org.sopt.dto.response.CommentResponse;
import org.sopt.repository.ArticleRepository;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentValidator commentValidator;

    @Transactional
    @Override
    public Long createComment(Long articleId, CommentRequest request) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 없습니다."));

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 없습니다."));

        commentValidator.validateLength(request.content());

        Comment comment = Comment.builder()
                .article(article)
                .member(member)
                .content(request.content())
                .build();

        return commentRepository.save(comment).getId();
    }

    @Transactional
    @Override
    public void updateComment(Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 없습니다."));

        commentValidator.validateWriter(comment, request.memberId());
        commentValidator.validateLength(request.content());

        comment.updateContent(request.content());
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 없습니다."));

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentResponse> getComments(Long articleId) {
        return commentRepository.findAllByArticleIdOrderByIdAsc(articleId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }
}
