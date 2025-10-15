package org.sopt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;

public interface MemberService {

	Long join(String name, LocalDate birthday, String email, GENDER gender);
	Optional<Member> findOne(Long memberId);
	List<Member> findAllMembers();
	Optional<Member> findByEmail(String email);
}
