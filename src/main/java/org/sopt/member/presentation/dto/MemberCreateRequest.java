package org.sopt.member.presentation.dto;

import java.time.LocalDate;

import org.sopt.member.domain.entity.enums.GENDER;

public record MemberCreateRequest(
	String name,
	LocalDate birthday,
	String email,
	GENDER gender
) {

}
