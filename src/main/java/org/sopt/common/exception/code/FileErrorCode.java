package org.sopt.common.exception.code;

import org.sopt.common.exception.code.base.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum FileErrorCode implements BaseErrorCode {
	// 500 INTERNAL SERVER ERROR
	FILE_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "❌ 파일 생성에 실패하였습니다."),
	FILE_READ_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "❌ 파일 전체 읽어들이기에 실패하였습니다."),
	FILE_WRITE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "❌ 파일 작성에 실패하였습니다."),
	FILE_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "❌ 파일 저장에 실패하였습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	FileErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() { return this.httpStatus; }

	public String getMessage() {
		return message;
	}
}
