package org.sopt.article.presentation.dto;

import java.util.List;

public record ArticleAllInfoResponse(
	List<ArticleInfoResponse> articleList
) {
}
