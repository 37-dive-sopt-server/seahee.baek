package org.sopt.controller.member;

import org.sopt.common.response.SuccessResponse;
import org.sopt.controller.member.dto.ArticleAllInfoResponse;
import org.sopt.controller.member.dto.ArticleCreateRequest;
import org.sopt.controller.member.dto.ArticleInfoResponse;
import org.sopt.domain.Member;
import org.sopt.exception.code.ArticleSuccessCode;
import org.sopt.service.ArticleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleServiceImpl articleService;
	private final ArticleMemberFacade articleMemberFacade;

	@PostMapping
	public ResponseEntity<SuccessResponse<ArticleInfoResponse>> createArticle(
		@RequestBody ArticleCreateRequest request
	) {
		Member member = articleMemberFacade.findById(request.memberId());
		ArticleInfoResponse response = articleService.createArticle(request, member);

		return ResponseEntity.ok()
			.body(SuccessResponse.of(ArticleSuccessCode.ARTICLE_CREATE_SUCCESS, response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<ArticleInfoResponse>> getArticleById(@PathVariable Long id) {
		ArticleInfoResponse response = articleService.findById(id);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(ArticleSuccessCode.ARTICLE_GET_SUCCESS, response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<ArticleAllInfoResponse>> getAllArticles() {
		ArticleAllInfoResponse response = articleService.findAll();
		return ResponseEntity.ok()
			.body(SuccessResponse.of(ArticleSuccessCode.ARTICLE_GET_SUCCESS, response));
	}
}
