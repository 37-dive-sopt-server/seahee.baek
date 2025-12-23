package org.sopt.article.presentation.dto;

public record CommentCreateResponse(
	Long id,
	String content,
	String memberName
) {
	public static CommentCreateResponse of(Long id, String content, String memberName) {
		return new CommentCreateResponse(id, content, memberName);
	}
}
