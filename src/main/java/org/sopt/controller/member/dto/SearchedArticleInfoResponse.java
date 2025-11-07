package org.sopt.controller.member.dto;

import java.time.LocalDateTime;

public record SearchedArticleInfoResponse(
	Long id,
	String title,
	LocalDateTime createdAt,
	Long memberId,
	String memberName
) {

	public static SearchedArticleInfoResponse of(
		Long id,
		String title,
		LocalDateTime createdAt,
		Long memberId,
		String memberName
	) {
		return new SearchedArticleInfoResponse(id, title, createdAt, memberId, memberName);
	}
}
