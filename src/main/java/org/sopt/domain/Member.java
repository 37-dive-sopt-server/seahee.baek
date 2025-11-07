package org.sopt.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.sopt.domain.enums.GENDER;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private LocalDate birthday;
	private String email;
	private GENDER gender;

	@OneToMany(mappedBy = "member")
	private List<Article> articles = new ArrayList<>();

	@Builder
	private Member(String name, LocalDate birthday, String email, GENDER gender, List<Article> articles) {
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.gender = gender;
		this.articles = articles;
	}

	public static Member create(final String name, final LocalDate birthday, final String email, final GENDER gender) {
		return Member.builder()
			.name(name)
			.birthday(birthday)
			.email(email)
			.gender(gender)
			.build();
	}

}