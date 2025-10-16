package org.sopt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;
import org.sopt.exception.DuplicatedEmailException;
import org.sopt.exception.NotFoundException;
import org.sopt.exception.util.ErrorMessage;
import org.sopt.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

	private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
	private static long sequence = 1L;

	public Long join(String name, String birthdayString, String email, String genderString) {
		LocalDate birthday = LocalDate.parse(birthdayString);
		GENDER gender = GENDER.fromString(genderString);
		if(findByEmail(email).isPresent()) {
			throw new DuplicatedEmailException(ErrorMessage.EMAIL_DUPLICATE.getMessage());
		}
		Member member = new Member(sequence++, name, birthday, email, gender);
		try {
			memberRepository.save(member);
		} catch (Exception e) {
			throw new RuntimeException("❌ 회원 등록 실패");
		}
		return member.getId();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findByEmail(String email) { return memberRepository.findByEmail(email); }

	public void deleteMemberById(Long memberId) {
		System.out.println(findOne(memberId));
		if(findOne(memberId).isEmpty()) {
			throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND.getMessage());
		}
		memberRepository.deleteById(memberId);
	}
}