package org.sopt.common.response;

import org.sopt.common.exception.code.base.BaseSuccessCode;

public record SuccessResponse<T>(
	int status,
	String message,
	T data
) {

	public static <T> SuccessResponse<T> of(BaseSuccessCode baseSuccesscode) {
		return new SuccessResponse<>(baseSuccesscode.getHttpStatus().value(), baseSuccesscode.getMessage(), null);
	}

	public static <T> SuccessResponse<T> of(BaseSuccessCode baseSuccesscode, T data) {
		return new SuccessResponse<>(baseSuccesscode.getHttpStatus().value(), baseSuccesscode.getMessage(), data);
	}
}
