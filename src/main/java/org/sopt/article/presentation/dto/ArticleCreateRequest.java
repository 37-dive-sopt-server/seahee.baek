package org.sopt.article.presentation.dto;

import org.sopt.article.domain.entity.enums.Tag;

public record ArticleCreateRequest(
	Long memberId,
	String title,
	String content,
	Tag tag
) {
}
