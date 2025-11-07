package org.sopt.exception.code;

import org.sopt.exception.base.BaseErrorCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {

	// 404 NOT FOUND
	ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 아티클을 찾지 못하였습니다."),

	// 409 CONFLICT
	ARTICLE_TITLE_DUPLICATED(HttpStatus.CONFLICT, "다른 아티클과 제목이 중복됩니다.")
	;

	private final HttpStatus httpStatus;
	private final String message;

}
