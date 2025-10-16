package org.sopt.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;
import org.sopt.service.MemberServiceImpl;

public class MemberController {

	private final MemberServiceImpl memberService = new MemberServiceImpl();

	public Long createMember(String name, String birthdayString, String email, String genderString) {
		return memberService.join(name, birthday, email, gender);
	}

	public Optional<Member> findMemberById(Long id) {
		return memberService.findOne(id);
	}

	public List<Member> getAllMembers() {
		return memberService.findAllMembers();
	}

	public void deleteMember(Long id) {
		memberService.deleteMemberById(id);
	}

}