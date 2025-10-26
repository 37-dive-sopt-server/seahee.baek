package org.sopt.exception.code;

import org.sopt.exception.base.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum FileSuccessCode implements BaseSuccessCode{

	;

	private final HttpStatus httpStatus;
	private final String message;

	FileSuccessCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() { return this.httpStatus; }

	public String getMessage() {
		return message;
	}

}
