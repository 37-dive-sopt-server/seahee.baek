package org.sopt.member.presentation.dto;

import java.time.LocalDate;

import org.sopt.member.domain.entity.Member;
import org.sopt.member.domain.entity.enums.GENDER;

public record MemberResponseDTO(
	Long id,
	String name,
	GENDER gender,
	LocalDate birthday,
	String email
) {
	public static MemberResponseDTO from(Member m) {
		return new MemberResponseDTO(
			m.getId(),
			m.getName(),
			m.getGender(),
			m.getBirthday(),
			m.getEmail()
		);
	}

	public static MemberResponseDTO of(Long id, String name, GENDER gender, LocalDate birthday, String email) {
		return new MemberResponseDTO(id, name, gender, birthday, email);
	}
}
