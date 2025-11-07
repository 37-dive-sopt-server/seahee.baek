package org.sopt.controller.article.dto;

import java.util.List;

public record ArticleAllInfoResponse(
	List<ArticleInfoResponse> articleList
) {
}
