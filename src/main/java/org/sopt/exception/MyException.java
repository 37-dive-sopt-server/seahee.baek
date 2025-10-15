package org.sopt.exception;

public class MyException extends RuntimeException {
	private final String message;

	public MyException(String message) {
		this.message = message;
	}
}
