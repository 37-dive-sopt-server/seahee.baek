package org.sopt.article.presentation.code;

import org.sopt.common.exception.code.base.BaseSuccessCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentSuccessCode implements BaseSuccessCode {

	// 201 CREATED
	COMMENT_CREATE_SUCCESS(HttpStatus.CREATED, "댓글 작성이 성공하였습니다"),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
