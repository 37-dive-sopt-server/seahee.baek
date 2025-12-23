package org.sopt.member.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.sopt.article.domain.entity.Article;
import org.sopt.article.domain.entity.Comment;
import org.sopt.member.domain.entity.enums.GENDER;

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
	private String password;
	private LocalDate birthday;
	private String email;
	private GENDER gender;

	@OneToMany(mappedBy = "member")
	private List<Article> articles = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Comment> comments = new ArrayList<>();

	@Builder
	private Member(String name, String password, LocalDate birthday, String email, GENDER gender) {
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.email = email;
		this.gender = gender;
	}

	public static Member create(final String name, final String password, final LocalDate birthday, final String email, final GENDER gender) {
		return Member.builder()
			.name(name)
			.password(password)
			.birthday(birthday)
			.email(email)
			.gender(gender)
			.build();
	}

}