package org.sopt.article.presentation.dto;

import jakarta.validation.constraints.Size;

public record CommentRequest(
	@Size(max = 300, message = "댓글은 300자 이하여야 합니다.")
	String content
) {
}
