package org.sopt.util.validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.sopt.domain.enums.GENDER;
import org.sopt.exception.MyException;
import org.sopt.exception.code.MemberErrorCode;

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
