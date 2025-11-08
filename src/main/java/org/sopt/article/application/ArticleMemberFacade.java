package org.sopt.article.application;

import org.sopt.member.domain.entity.Member;
import org.sopt.member.domain.service.MemberServiceImpl;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArticleMemberFacade {

	private final MemberServiceImpl memberService;

	public Member findById(Long id) { return memberService.findById(id); }
}
