package org.sopt.service;

import java.util.List;
import java.util.Optional;

import org.sopt.domain.Member;

public interface MemberService {

	Long join(String name, String birthday, String email, String gender);
	Optional<Member> findOne(Long memberId);
	List<Member> findAllMembers();
	Optional<Member> findByEmail(String email);
	void deleteMemberById(Long memberId);
}
