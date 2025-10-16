package org.sopt.util.validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.sopt.exception.BadRequestException;
import org.sopt.exception.InvalidAgeException;
import org.sopt.exception.util.ErrorMessage;

public final class MemberInputValidator {
	private MemberInputValidator() {}

	public static String validEmailChecker(String email) {
		nonEmptyChecker(email);
		if (!email.contains("@")) {
			throw new BadRequestException(ErrorMessage.INVALID_EMAIL_ADDRESS.getMessage());
		}
		return email;
	}

	public static String validAgeChecker(String birthday) {
		nonEmptyChecker(birthday);
		if(!birthday.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new BadRequestException(ErrorMessage.INVALID_BIRTH_FORMAT.getMessage());
		}

		LocalDate today = LocalDate.now();
		int age = Period.between(LocalDate.parse(birthday), today).getYears();
		if( age <= 20 || age >= 100 ) {
			throw new InvalidAgeException(ErrorMessage.INVALID_AGE.getMessage());
		}
		return birthday;
	}

	public static String validGenderChecker(String gender) {
		List<String> genderList = List.of("여자", "남자");
		if(!genderList.contains(gender)) {
			throw new BadRequestException(ErrorMessage.INVALID_GENDER.getMessage());
		}
		return gender;
	}

	public static String nonEmptyChecker(String content) {
		if(content.isEmpty()) {
			throw new BadRequestException(ErrorMessage.EMPTY_INPUT.getMessage());
		}
		if(content.contains(" ")) {
			throw new BadRequestException(ErrorMessage.NOT_ALLOWED_SPACE.getMessage());
		}
		return content;
	}

	public static Long validIdChecker(String id) {
		try {
			return Long.parseLong(id);
		}
		catch(NumberFormatException e) {
			throw new BadRequestException(ErrorMessage.INVALID_NUMBER.getMessage());
		}
	}
}
