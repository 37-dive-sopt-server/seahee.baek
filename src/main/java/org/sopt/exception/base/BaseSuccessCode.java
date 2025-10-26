package org.sopt.exception.base;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
	HttpStatus getHttpStatus();

	String getMessage();
}
