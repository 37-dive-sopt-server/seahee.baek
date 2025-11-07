package org.sopt.member.presentation.dto;

import java.time.LocalDate;

import org.sopt.member.domain.entity.enums.GENDER;

public record MemberInfoResponse(
	Long id,
	String name,
	LocalDate birthday,
	String email,
	GENDER gender
) {

	public static MemberInfoResponse of(
		Long id,
		String name,
		LocalDate birthday,
		String email,
		GENDER gender
	) { return new MemberInfoResponse(id, name, birthday, email, gender); }

}
