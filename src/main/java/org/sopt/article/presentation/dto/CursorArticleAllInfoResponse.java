package org.sopt.article.presentation.dto;

import java.util.List;

public record CursorArticleAllInfoResponse(
	List<ArticleAllInfoResponse> articles,
	Long nextCursor,
	boolean hasNext
) {
}
