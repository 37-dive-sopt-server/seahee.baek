package org.sopt.common.exception.code;

import org.sopt.common.exception.code.base.BaseSuccessCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ArticleSuccessCode implements BaseSuccessCode {

	// 200 OK
	ARTICLE_GET_SUCCESS(HttpStatus.OK, "아티클이 성공적으로 조회되었습니다."),
	ARTICLE_SEARCH_SUCCESS(HttpStatus.OK, "아티클이 성공적으로 검색되었습니다."),

	// 201 CREATED
	ARTICLE_CREATE_SUCCESS(HttpStatus.CREATED, "아티클이 성공적으로 작성되었습니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
