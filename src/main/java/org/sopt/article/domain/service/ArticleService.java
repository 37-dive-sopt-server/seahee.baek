package org.sopt.article.domain.service;

import org.sopt.article.presentation.dto.ArticleAllInfoResponse;
import org.sopt.article.presentation.dto.ArticleCreateRequest;
import org.sopt.article.presentation.dto.ArticleInfoResponse;
import org.sopt.member.domain.entity.Member;

public interface ArticleService {

	ArticleInfoResponse createArticle(ArticleCreateRequest request, Member member);

	ArticleInfoResponse findById(Long id);

	ArticleAllInfoResponse findAll();

}
