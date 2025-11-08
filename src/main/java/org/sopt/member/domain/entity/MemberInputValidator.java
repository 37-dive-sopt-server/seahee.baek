package org.sopt.member.domain.entity;

import java.time.LocalDate;
import java.time.Period;

import org.sopt.common.exception.MyException;
import org.sopt.common.exception.code.MemberErrorCode;

public final class MemberInputValidator {
	private MemberInputValidator() {}

	public static void validEmailChecker(String email) {
		nonEmptyChecker(email);
		if (!email.contains("@")) {
			throw new MyException(MemberErrorCode.INVALID_EMAIL_ADDRESS);
		}
	}

	public static void validAgeChecker(LocalDate birthday) {
		LocalDate today = LocalDate.now();
		int age = Period.between(birthday, today).getYears();
		if( age <= 20 || age >= 100 ) {
			throw new MyException(MemberErrorCode.INVALID_AGE);
		}
	}

	public static void nonEmptyChecker(String content) {
		if(content.isEmpty()) {
			throw new MyException(MemberErrorCode.EMPTY_INPUT);
		}
		if(content.contains(" ")) {
			throw new MyException(MemberErrorCode.NOT_ALLOWED_SPACE);
		}
	}
}
