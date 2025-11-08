package org.sopt.common.exception.code;

import org.sopt.common.exception.code.base.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements BaseErrorCode {
	// 400 BAD REQUEST
	INVALID_EMAIL_ADDRESS(HttpStatus.BAD_REQUEST, "⚠️ 올바른 이메일 형식이 아닙니다. @ 문자를 포함해야합니다."),
	INVALID_AGE(HttpStatus.BAD_REQUEST,"⚠️ 나이는 20살 이상, 100살 이하여야 합니다."),
	INVALID_BIRTH_FORMAT(HttpStatus.BAD_REQUEST,"⚠️ 올바른 날짜 형식이 아닙니다."),
	NOT_ALLOWED_SPACE(HttpStatus.BAD_REQUEST,"⚠️ 입력에 공백이 포함될 수 없습니다."),
	EMPTY_INPUT(HttpStatus.BAD_REQUEST,"⚠️ 값을 입력해주세요."),
	INVALID_GENDER(HttpStatus.BAD_REQUEST,"⚠️ 성별은 '여자' 혹은 '남자' 중 하나여야 합니다."),
	INVALID_NUMBER(HttpStatus.BAD_REQUEST,"❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요."),

	// 404 NOT FOUND
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "❌ ID에 해당하는 멤버가 저장되어 있지 않습니다."),

	// 409 CONFLICT
	EMAIL_DUPLICATE(HttpStatus.CONFLICT, "⚠️ 이미 존재하는 이메일 주소입니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;

	MemberErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() { return this.httpStatus; }

	public String getMessage() {
		return message;
	}
}
