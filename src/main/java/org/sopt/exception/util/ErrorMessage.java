package org.sopt.exception.util;

public enum ErrorMessage {
	EMAIL_DUPLICATE("⚠️ 이미 존재하는 이메일 주소입니다."),
	INVALID_EMAIL_ADDRESS("⚠️ 올바른 이메일 형식이 아닙니다. @ 문자를 포함해야합니다."),
	INVALID_AGE("⚠️ 나이는 20살 이상, 100살 이하여야 합니다."),
	INVALID_BIRTH_FORMAT("⚠️ 올바른 날짜 형식이 아닙니다."),
	NOT_ALLOWED_SPACE("⚠️ 입력에 공백이 포함될 수 없습니다."),
	EMPTY_INPUT("⚠️ 값을 입력해주세요."),
	INVALID_GENDER("⚠️ 성별은 '여자' 혹은 '남자' 중 하나여야 합니다."),
	INVALID_NUMBER("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요."),
	MEMBER_NOT_FOUND("❌ ID에 해당하는 멤버가 저장되어 있지 않습니다."),

	FILE_CREATE_FAILED("❌ 파일 생성에 실패하였습니다."),
	FILE_READ_FAILED("❌ 파일 전체 읽어들이기에 실패하였습니다."),
	FILE_WRITE_FAILED("❌ 파일 작성에 실패하였습니다."),
	FILE_SAVE_FAILED("❌ 파일 저장에 실패하였습니다.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
