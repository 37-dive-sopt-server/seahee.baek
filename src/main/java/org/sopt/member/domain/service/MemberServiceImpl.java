package org.sopt.member.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.sopt.member.presentation.dto.MemberAllInfoResponse;
import org.sopt.member.presentation.dto.MemberCreateRequest;
import org.sopt.member.presentation.dto.MemberInfoResponse;
import org.sopt.member.domain.entity.Member;

import org.sopt.common.exception.MyException;
import org.sopt.common.exception.code.MemberErrorCode;

import org.sopt.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberInfoResponse join(MemberCreateRequest request) {
		if(findByEmail(request.email()).isPresent()) {
			throw new MyException(MemberErrorCode.EMAIL_DUPLICATE);
		}
		Member member = Member.create(request.name(), request.birthday(), request.email(), request.gender());
		memberRepository.save(member);

		return MemberInfoResponse.of(
			member.getId(),
			member.getName(),
			member.getBirthday(),
			member.getEmail(),
			member.getGender()
		);
	}

	public MemberInfoResponse getMemberInfoResponse(Long memberId) {
		Member member = findById(memberId);
		return MemberInfoResponse.of(
			member.getId(),
			member.getName(),
			member.getBirthday(),
			member.getEmail(),
			member.getGender()
		);
	}

	public MemberAllInfoResponse findAllMembers() {
		List<MemberInfoResponse> memberList = memberRepository.findAll()
			.stream()
			.map(member -> MemberInfoResponse.of(
				member.getId(),
				member.getName(),
				member.getBirthday(),
				member.getEmail(),
				member.getGender()
			))
			.collect(Collectors.toList());
		return new MemberAllInfoResponse(memberList);
	}

	public void deleteMemberById(Long memberId) {
		Member member = findById(memberId);
		memberRepository.deleteById(member.getId());
	}

	public Member findById(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new MyException(MemberErrorCode.MEMBER_NOT_FOUND));
	}

	private Optional<Member> findByEmail(String email) { return memberRepository.findByEmail(email); }
}