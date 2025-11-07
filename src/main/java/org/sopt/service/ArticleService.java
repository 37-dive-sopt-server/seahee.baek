package org.sopt.service;

import org.sopt.controller.member.dto.ArticleAllInfoResponse;
import org.sopt.controller.member.dto.ArticleCreateRequest;
import org.sopt.controller.member.dto.ArticleInfoResponse;
import org.sopt.domain.Member;

public interface ArticleService {

	ArticleInfoResponse createArticle(ArticleCreateRequest request, Member member);

	ArticleInfoResponse findById(Long id);

	ArticleAllInfoResponse findAll();

}
