package org.sopt.common.validator;

import org.sopt.domain.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator {

    public void validateLength(String content) {
        if (content == null || content.length() > 300) {
            throw new IllegalArgumentException("댓글은 300자 이내여야 합니다.");
        }
    }

    public void validateWriter(Comment comment, Long requestMemberId) {
        if (!comment.getMember().getId().equals(requestMemberId)) {
            throw new IllegalArgumentException("작성자만 댓글을 수정/삭제할 수 있습니다.");
        }
    }
}
