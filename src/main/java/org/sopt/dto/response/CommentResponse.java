package org.sopt.dto.response;

import org.sopt.domain.Comment;

public record CommentResponse(
        Long commentId,
        String content,
        String writerName
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getMember().getName()
        );
    }
}
