package org.sopt.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository{

	private static final Map<Long, Member> store = new HashMap<>();
	private static Long finalId = 1L;

	public Member saveMember(String name, LocalDate birthday, String email, GENDER gender) {
		Member member = new Member(finalId++, name, birthday, email, gender);
		store.put(member.getId(), member);
		return member;

	}

	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	public Optional<Member> findByEmail(String email) {
		return store.values()
			.stream()
			.filter(member -> member.getEmail().equals(email))
			.findFirst();
	}

	public void deleteById(Long memberId) { store.remove(memberId); }
}