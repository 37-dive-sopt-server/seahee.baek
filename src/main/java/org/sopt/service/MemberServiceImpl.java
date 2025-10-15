package org.sopt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;
import org.sopt.exception.DuplicatedEmailException;
import org.sopt.exception.util.ErrorMessage;
import org.sopt.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

	private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
	private static long sequence = 1L;

	public Long join(String name, LocalDate birthday, String email, GENDER gender) {
		System.out.println("이메일 유효검사");
		boolean a = findByEmail(email).isPresent();
		System.out.println("a = " + a);
		if(a) {
			throw new DuplicatedEmailException(ErrorMessage.EMAIL_DUPLICATE.getMessage());
		}
		System.out.println("회원 객체 생성");
		Member member = new Member(sequence++, name, birthday, email, gender);

		System.out.println("회원 저장");
		memberRepository.save(member);
		return member.getId();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findByEmail(String email) { return memberRepository.findByEmail(email); }
}