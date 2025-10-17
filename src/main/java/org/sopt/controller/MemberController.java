package org.sopt.controller;

import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.service.MemberService;

public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	public Long createMember(String name, String birthdayString, String email, String genderString) {
		return memberService.join(name, birthdayString, email, genderString);
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