package org.sopt.article.presentation.dto;

import org.sopt.article.domain.entity.enums.TAG;

public record ArticleCreateRequest(
	Long memberId,
	String title,
	String content,
	TAG tag
) {
}
