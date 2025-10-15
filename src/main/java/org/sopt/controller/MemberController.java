package org.sopt.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;
import org.sopt.exception.BadRequestException;
import org.sopt.exception.InvalidAgeException;
import org.sopt.exception.util.ErrorMessage;
import org.sopt.service.MemberServiceImpl;

public class MemberController {

	private final MemberServiceImpl memberService = new MemberServiceImpl();

	public Long createMember(String name, String birthdayString, String email, String genderString) {
		LocalDate birthday = LocalDate.parse(birthdayString);
		GENDER gender = GENDER.fromString(genderString);

		return memberService.join(name, birthday, email, gender);
	}

	public Optional<Member> findMemberById(Long id) {
		return memberService.findOne(id);
	}

	public List<Member> getAllMembers() {
		return memberService.findAllMembers();
	}

	public String validEmailChecker(String email) {
		nonEmptyChecker(email);
		if (!email.contains("@")) {
			throw new BadRequestException(ErrorMessage.INVALID_EMAIL_ADDRESS.getMessage());
		}
		return email;
	}

	public String validAgeChecker(String birthday) {
		nonEmptyChecker(birthday);
		if(!birthday.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new BadRequestException(ErrorMessage.INVALID_BIRTH_FORMAT.getMessage());
		}

		LocalDate today = LocalDate.now();
		int age = Period.between(LocalDate.parse(birthday), today).getYears();
		if( age >= 20 && age <= 100 ) {
			throw new InvalidAgeException(ErrorMessage.INVALID_AGE.getMessage());
		}
		return birthday;
	}

	public String validGenderChecker(String gender) {
		List<String> genderList = List.of("여자", "남자");
		if(!genderList.contains(gender)) {
			throw new BadRequestException(ErrorMessage.INVALID_GENDER.getMessage());
		}
		return gender;
	}

	public String nonEmptyChecker(String content) {
		if(content.isEmpty()) {
			throw new BadRequestException(ErrorMessage.EMPTY_INPUT.getMessage());
		}
		if(content.contains(" ")) {
			throw new BadRequestException(ErrorMessage.EMPTY_INPUT.getMessage());
		}
		return content;
	}

}