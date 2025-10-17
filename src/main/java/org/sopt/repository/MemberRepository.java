package org.sopt.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;

public interface MemberRepository {
	Member saveMember(String name, LocalDate birthday, String email, GENDER gender);
	Optional<Member> findById(Long id);
	List<Member> findAll();
	Optional<Member> findByEmail(String email);
	void deleteById(Long memberId);
}