package org.sopt.common.exception;

import org.sopt.common.exception.code.base.BaseErrorCode;

public class MyException extends RuntimeException {
	private final BaseErrorCode baseErrorCode;

	public MyException(BaseErrorCode baseErrorCode) {
		super(baseErrorCode.getMessage());
		this.baseErrorCode = baseErrorCode;
	}

	public BaseErrorCode getBaseErrorCode() { return baseErrorCode; }
}
