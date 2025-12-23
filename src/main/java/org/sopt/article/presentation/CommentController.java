package org.sopt.article.presentation;

import org.sopt.article.domain.service.CommentService;
import org.sopt.article.presentation.code.CommentSuccessCode;
import org.sopt.article.presentation.dto.CommentCreateRequest;
import org.sopt.article.presentation.dto.CommentCreateResponse;
import org.sopt.common.response.SuccessResponse;
import org.sopt.global.auth.annotation.CurrentMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/{articleId}")
	public ResponseEntity<SuccessResponse<CommentCreateResponse>> createComment(
		@RequestBody CommentCreateRequest request,
		@PathVariable Long articleId,
		@CurrentMember Long memberId
	) {
		CommentCreateResponse response = commentService.createComment(request, articleId, memberId);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(CommentSuccessCode.COMMENT_CREATE_SUCCESS, response));
	}

}
