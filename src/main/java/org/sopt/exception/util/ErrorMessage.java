package org.sopt.exception.util;

public enum ErrorMessage {
	EMAIL_DUPLICATE("이미 존재하는 이메일 주소입니다.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
