package org.sopt.article.presentation.dto;

import java.time.LocalDateTime;

import org.sopt.article.domain.entity.enums.TAG;

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
