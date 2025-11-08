package org.sopt.member.domain.service;

import org.sopt.member.presentation.dto.MemberAllInfoResponse;
import org.sopt.member.presentation.dto.MemberCreateRequest;
import org.sopt.member.presentation.dto.MemberInfoResponse;
import org.sopt.member.domain.entity.Member;

public interface MemberService {

	MemberInfoResponse join(MemberCreateRequest memberCreateRequest);
	MemberInfoResponse getMemberInfoResponse(Long memberId);
	MemberAllInfoResponse findAllMembers();
	Member findById(Long id);
	void deleteMemberById(Long memberId);

}
