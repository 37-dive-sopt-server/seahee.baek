package org.sopt.common.exception.code.base;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
	HttpStatus getHttpStatus();

	String getMessage();
}
