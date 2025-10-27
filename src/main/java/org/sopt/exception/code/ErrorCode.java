package org.sopt.exception.code;

import org.sopt.exception.base.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum ErrorCode implements BaseErrorCode {
	/*
	400 BAD REQUEST
	 */
	INVALID_FIELD_ERROR(HttpStatus.BAD_REQUEST, "요청 필드 값이 유효하지 않습니다."),
	MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다."),
	MISSING_HEADER(HttpStatus.BAD_REQUEST, "필수 요청 헤더가 누락되었습니다."),
	TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "요청 값 타입이 올바르지 않습니다."),
	INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않습니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}
}
