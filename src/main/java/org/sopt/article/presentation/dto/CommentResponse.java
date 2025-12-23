package org.sopt.article.presentation.dto;

public record CommentResponse(
	Long id,
	String content,
	String memberName
) {
	public static CommentResponse of(Long id, String content, String memberName) {
		return new CommentResponse(id, content, memberName);
	}
}
