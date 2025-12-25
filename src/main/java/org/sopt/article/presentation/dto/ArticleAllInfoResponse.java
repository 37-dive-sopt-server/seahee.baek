package org.sopt.article.presentation.dto;

import java.time.LocalDateTime;

import org.sopt.article.domain.entity.enums.Tag;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ArticleAllInfoResponse(
	Long id,
	String title,
	Tag tag,

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdAt,

	Long memberId,
	String memberName
) {
	public static ArticleAllInfoResponse of(
		Long id, String title, Tag tag, LocalDateTime createdAt, Long memberId, String memberName
	) {
		return new ArticleAllInfoResponse(id, title, tag, createdAt, memberId, memberName);
	}
}
