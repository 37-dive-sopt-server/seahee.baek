package org.sopt.controller.article.dto;

import java.time.LocalDateTime;

import org.sopt.domain.enums.TAG;

public record ArticleInfoResponse(
	Long id,
	String title,
	String content,
	TAG tag,
	LocalDateTime createdAt,
	Long memberId
) {

	public static ArticleInfoResponse of(
		Long id,
		String title,
		String content,
		TAG tag,
		LocalDateTime createdAt,
		Long memberId
	) {
		return new ArticleInfoResponse(id, title, content, tag, createdAt, memberId);
	}
}
