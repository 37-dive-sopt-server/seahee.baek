package org.sopt.controller.article.dto;

import java.util.List;

public record SearchedArticlesResponse(
	List<SearchedArticleInfoResponse> articleSearchList,
	int page,
	int totalPages,
	long totalElements
) {
}
