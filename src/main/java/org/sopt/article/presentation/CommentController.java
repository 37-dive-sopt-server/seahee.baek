package org.sopt.article.presentation;

import org.sopt.article.domain.service.CommentService;
import org.sopt.article.presentation.code.CommentSuccessCode;
import org.sopt.article.presentation.dto.CommentRequest;
import org.sopt.article.presentation.dto.CommentResponse;
import org.sopt.common.response.SuccessResponse;
import org.sopt.global.auth.annotation.CurrentMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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
	public ResponseEntity<SuccessResponse<CommentResponse>> createComment(
		@RequestBody CommentRequest request,
		@PathVariable Long articleId,
		@CurrentMember Long memberId
	) {
		CommentResponse response = commentService.createComment(request, articleId, memberId);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(CommentSuccessCode.COMMENT_CREATE_SUCCESS, response));
	}

	@PatchMapping("/{commentId}")
	public ResponseEntity<SuccessResponse<CommentResponse>> updateComment(
		@RequestBody CommentRequest request,
		@PathVariable Long commentId,
		@CurrentMember Long memberId
	) {
		CommentResponse response = commentService.updateComment(request, commentId, memberId);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(CommentSuccessCode.COMMENT_UPDATE_SUCCESS, response));
	}

}
