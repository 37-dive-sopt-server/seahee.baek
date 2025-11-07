package org.sopt.controller.member;

import org.sopt.domain.Member;
import org.sopt.service.MemberServiceImpl;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArticleMemberFacade {

	private final MemberServiceImpl memberService;

	public Member findById(Long id) { return memberService.findById(id); }
}
