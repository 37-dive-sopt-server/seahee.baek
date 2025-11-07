package org.sopt.controller.article.dto;

import org.sopt.domain.enums.TAG;

public record ArticleCreateRequest(
	Long memberId,
	String title,
	String content,
	TAG tag
) {
}
