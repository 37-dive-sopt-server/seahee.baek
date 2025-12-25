package org.sopt.article.domain.service;

import org.sopt.article.presentation.dto.ArticleCreateRequest;
import org.sopt.article.presentation.dto.ArticleInfoResponse;
import org.sopt.article.presentation.dto.CursorArticleAllInfoResponse;
import org.sopt.member.domain.entity.Member;

public interface ArticleService {

	ArticleInfoResponse createArticle(ArticleCreateRequest request, Member member);
	CursorArticleAllInfoResponse findAll(Long cursor, int size);

}
