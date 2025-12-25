package org.sopt.controller;

import org.sopt.common.SuccessResponse;
import org.sopt.dto.request.CommentRequest;
import org.sopt.dto.response.ApiCode;
import org.sopt.dto.response.CommentResponse;
import org.sopt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<SuccessResponse<Long>> createComment(
            @PathVariable Long articleId,
            @RequestBody CommentRequest request
    ) {
        Long commentId = commentService.createComment(articleId, request);
        return ResponseEntity
                .status(ApiCode.SUCCESS_CREATE_COMMENT.status())
                .body(SuccessResponse.of(ApiCode.SUCCESS_CREATE_COMMENT, commentId));
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<SuccessResponse<Long>> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest request
    ) {
        commentService.updateComment(commentId, request);
        return ResponseEntity
                .status(ApiCode.SUCCESS_UPDATE_COMMENT.status())
                .body(SuccessResponse.of(ApiCode.SUCCESS_UPDATE_COMMENT, commentId));
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<SuccessResponse<Long>> deleteComment(
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId);
        return ResponseEntity
                .status(ApiCode.SUCCESS_DELETE_COMMENT.status())
                .body(SuccessResponse.of(ApiCode.SUCCESS_DELETE_COMMENT, commentId));
    }


    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<SuccessResponse<List<CommentResponse>>> getComments(
            @PathVariable Long articleId
    ) {
        List<CommentResponse> response = commentService.getComments(articleId);
        return ResponseEntity
                .status(ApiCode.SUCCESS_FIND_COMMENT.status())
                .body(SuccessResponse.of(ApiCode.SUCCESS_FIND_COMMENT, response));
    }
}
