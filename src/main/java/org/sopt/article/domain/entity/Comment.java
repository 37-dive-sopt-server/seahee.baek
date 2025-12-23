package org.sopt.article.domain.entity;

import org.sopt.member.domain.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleId")
	Article article;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	Member member;

	@Builder
	private Comment(String content, Article article, Member member) {
		this.content = content;
		this.article = article;
		this.member = member;
	}

	public static Comment create(String content, Article article, Member member) {
		return Comment.builder()
			.content(content)
			.article(article)
			.member(member)
			.build();
	}
}
