package org.sopt.article.presentation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record CommentResponse(
	Long id,
	String content,
	String memberName,

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdAt
) {
	public static CommentResponse of(Long id, String content, String memberName, LocalDateTime createdAt) {
		return new CommentResponse(id, content, memberName, createdAt);
	}
}
