package org.sopt.article.exception.code;

import org.sopt.common.exception.code.base.BaseErrorCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {

	// 403 FORBIDDEN
	COMMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "댓글에 접근할 권한이 없습니다"),

	// 404 NOT FOUND
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다");

	private final HttpStatus httpStatus;
	private final String message;

}
