package org.sopt.controller.member.dto;

import java.time.LocalDate;

import org.sopt.domain.enums.GENDER;

public record MemberCreateRequest(
	String name,
	LocalDate birthday,
	String email,
	GENDER gender
) {

}
