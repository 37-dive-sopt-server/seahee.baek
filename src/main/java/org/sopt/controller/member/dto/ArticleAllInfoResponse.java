package org.sopt.controller.member.dto;

import java.util.List;

public record ArticleAllInfoResponse(
	List<ArticleInfoResponse> articleList
) {
}
