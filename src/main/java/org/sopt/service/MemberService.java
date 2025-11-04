package org.sopt.service;

import java.util.Optional;

import org.sopt.controller.member.dto.MemberAllInfoResponse;
import org.sopt.controller.member.dto.MemberCreateRequest;
import org.sopt.controller.member.dto.MemberInfoResponse;
import org.sopt.domain.Member;

public interface MemberService {

	MemberInfoResponse join(MemberCreateRequest memberCreateRequest);
	MemberInfoResponse getMemberInfoResponse(Long memberId);
	MemberAllInfoResponse findAllMembers();
	Member findById(Long id);
	void deleteMemberById(Long memberId);

}
