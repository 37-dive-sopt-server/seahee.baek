package org.sopt.article.domain.repository;

import java.util.List;

import org.sopt.article.domain.entity.Article;
import org.sopt.article.domain.entity.QArticle;
import org.sopt.member.domain.entity.QMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

	private final JPAQueryFactory query;

	@Override
	public Page<Article> search(String searchWord, String memberName, Pageable pageable) {
		QArticle a = QArticle.article;
		QMember m = QMember.member;

		BooleanBuilder where = new BooleanBuilder();

		if (memberName != null && !memberName.isBlank()) {
			where.and(m.name.containsIgnoreCase(memberName));
		}

		if (searchWord != null && !searchWord.isBlank()) {
			where.and(a.title.containsIgnoreCase(searchWord));
		}

		List<Article> content = query
			.selectFrom(a)
			.join(a.member, m).fetchJoin()
			.where(where)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(a.id.desc())
			.fetch();

		Long articleCount = query
			.select(a.count())
			.from(a)
			.join(a.member, m)
			.where(where)
			.fetchOne();

		long totalElements = (articleCount == null) ? 0L : articleCount;

		return new PageImpl<>(content, pageable, totalElements);
	}
}
