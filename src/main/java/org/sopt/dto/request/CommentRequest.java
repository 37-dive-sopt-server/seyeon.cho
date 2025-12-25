package org.sopt.dto.request;

public record CommentRequest(
        Long memberId,
        String content
) {
}
