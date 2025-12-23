package org.sopt.article.presentation.dto;

import java.time.LocalDateTime;

public record CommentResponse(
	Long id,
	String content,
	String memberName,
	LocalDateTime createdAt
) {
	public static CommentResponse of(Long id, String content, String memberName, LocalDateTime createdAt) {
		return new CommentResponse(id, content, memberName, createdAt);
	}
}
