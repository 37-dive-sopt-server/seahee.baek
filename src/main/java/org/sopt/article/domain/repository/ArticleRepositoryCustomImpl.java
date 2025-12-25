package org.sopt.article.domain.repository;

import java.util.List;

import org.sopt.article.domain.entity.Article;
import org.sopt.article.domain.entity.QArticle;
import org.sopt.member.domain.entity.QMember;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

	private final JPAQueryFactory query;

	@Override
	public List<Article> search(Long cursor, int size, String searchWord, String memberName) {
		QArticle a = QArticle.article;
		QMember m = QMember.member;

		BooleanBuilder where = new BooleanBuilder();

		if (cursor != null) {
			where.and(a.id.lt(cursor));
		}

		if (memberName != null && !memberName.isBlank()) {
			where.and(m.name.containsIgnoreCase(memberName));
		}

		if (searchWord != null && !searchWord.isBlank()) {
			where.and(a.title.containsIgnoreCase(searchWord));
		}

		return query
			.selectFrom(a)
			.join(a.member, m).fetchJoin()
			.where(where)
			.orderBy(a.id.desc())
			.limit(size+1)
			.fetch();
	}

	public List<Article> findNextArticles(Long cursor, int size) {
		QArticle a = QArticle.article;
		BooleanBuilder where = new BooleanBuilder();

		if (cursor != null) {
			where.and(a.id.lt(cursor));
		}

		return query
			.selectFrom(a)
			.join(a.member).fetchJoin()
			.where(where)
			.orderBy(a.id.desc())
			.limit(size+1)
			.fetch();
	}
}
