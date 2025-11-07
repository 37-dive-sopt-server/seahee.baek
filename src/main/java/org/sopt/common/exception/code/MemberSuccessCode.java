package org.sopt.common.exception.code;

import org.sopt.common.exception.code.base.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum MemberSuccessCode implements BaseSuccessCode {

	// 200 OK
	MEMBER_GET_SUCCESS(HttpStatus.OK, "멤버 정보 조회가 성공하였습니다."),
	MEMBER_ALL_GET_SUCCESS(HttpStatus.OK, "모든 멤버 정보 조회가 성공하였습니다."),
	MEMBER_DELETE_SUCCESS(HttpStatus.OK, "멤버 삭제가 성공하였습니다." ),

	// 201 CREATED
	MEMBER_CREATE_SUCCESS(HttpStatus.CREATED, "멤버 등록이 성공하였습니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;

	MemberSuccessCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() { return this.httpStatus; }

	public String getMessage() {
		return message;
	}
}
