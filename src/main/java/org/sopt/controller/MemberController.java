package org.sopt.controller;

import static java.time.chrono.JapaneseEra.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;
import org.sopt.service.MemberServiceImpl;

public class MemberController {

	private final MemberServiceImpl memberService = new MemberServiceImpl();

	public Long createMember(String name, String birthdayString, String email, String genderString) {
		LocalDate birthday = LocalDate.parse(birthdayString);
		GENDER gender = GENDER.fromString(genderString);

		System.out.println("controller 문제 없음");

		return memberService.join(name, birthday, email, gender);
	}

	public Optional<Member> findMemberById(Long id) {
		return memberService.findOne(id);
	}

	public List<Member> getAllMembers() {
		return memberService.findAllMembers();
	}

	public boolean validEmailChecker(String email) {
		nonEmptyChecker(email);
		if (!email.contains("@")) {
			System.out.println("⚠️ 올바른 이메일 형식이 아닙니다. @ 문자를 포함해야합니다.");
			return false;
		}
		return true;
	}

	public boolean validAgeChecker(String birthday) {
		nonEmptyChecker(birthday);
		if(!birthday.matches("\\d{4}-\\d{2}-\\d{2}")) {
			System.out.println("⚠️ 날짜 형식에 맞춰 입력해주세요.");
			return false;
		}

		LocalDate today = LocalDate.now();
		int age = Period.between(LocalDate.parse(birthday), today).getYears();
		if( age >= 20 && age <= 100 ) {
			return true;
		}
		else {
			System.out.println("️⚠️ 나이는 20살 이상, 100살 이하여야 합니다.");
			return false;
		}
	}

	public boolean validGenderChecker(String gender) {
		for (GENDER g : GENDER.values()) {
			if(g.getGender().equals(gender)) {
				return true;
			}
		}
			System.out.println("️️⚠️ 성별은 '여자' 혹은 '남자' 중 하나여야 합니다.");
			return false;
	}

	public boolean nonEmptyChecker(String content) {
		if(content.isEmpty()) {
			System.out.println("⚠️ 값을 입력해주세요.");
			return false;
		}
		if(content.contains(" ")) {
			System.out.println("⚠️ 입력에 공백이 포함될 수 없습니다.");
			return false;
		}
		return true;
	}

}