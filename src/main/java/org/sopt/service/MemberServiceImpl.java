package org.sopt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.sopt.controller.member.dto.MemberAllInfoResponse;
import org.sopt.controller.member.dto.MemberCreateRequest;
import org.sopt.controller.member.dto.MemberInfoResponse;
import org.sopt.domain.Member;

import org.sopt.exception.MyException;
import org.sopt.exception.code.MemberErrorCode;

import org.sopt.repository.MemberRepository;
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