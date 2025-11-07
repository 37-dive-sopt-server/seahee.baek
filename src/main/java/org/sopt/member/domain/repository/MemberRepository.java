package org.sopt.member.domain.repository;

import java.util.Optional;

import org.sopt.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmail(String email);

}
