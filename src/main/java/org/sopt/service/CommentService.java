package org.sopt.service;

import org.sopt.dto.request.CommentRequest;
import org.sopt.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getComments(Long articleId);
    Long createComment(Long articleId, CommentRequest request);
    void updateComment(Long commentId, CommentRequest request);
    void deleteComment(Long commentId);
}
