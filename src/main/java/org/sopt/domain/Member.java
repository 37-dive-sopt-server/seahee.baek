package org.sopt.domain;

import java.time.LocalDate;

import org.sopt.domain.enums.GENDER;

public class Member {

	private final Long id;
	private final String name;
	private final LocalDate birthday;
	private final String email;
	private final GENDER gender;

	public Member(Long id, String name, LocalDate birthday, String email, GENDER gender) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public GENDER getGender() {
		return gender;
	}
}