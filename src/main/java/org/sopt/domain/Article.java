package org.sopt.domain;

import java.time.LocalDateTime;

import org.sopt.domain.enums.TAG;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article", uniqueConstraints = {
	@UniqueConstraint(name = "uk_article_title", columnNames = {"title"})
})
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TAG tag;

	private final LocalDateTime createdAt = LocalDateTime.now();

	private String title;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;

	@Builder
	public Article(TAG tag, String title, String content, Member member) {
		this.tag = tag;
		this.title = title;
		this.content = content;
		this.member = member;
	}

	public static Article create(String title, String content, Member member, TAG tag) {
		return Article.builder()
			.title(title)
			.content(content)
			.member(member)
			.tag(tag)
			.build();
	}

}
