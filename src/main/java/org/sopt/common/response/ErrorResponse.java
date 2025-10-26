package org.sopt.common.response;

import org.sopt.exception.base.BaseErrorCode;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
	int status,
	String message
) {

	public static ErrorResponse of(BaseErrorCode baseErrorCode) {
		return new ErrorResponse(baseErrorCode.getHttpStatus().value(), baseErrorCode.getMessage());
	}

	public static ErrorResponse of(HttpStatus httpStatus, String message) {
		return new ErrorResponse(httpStatus.value(), message);
	}
}
