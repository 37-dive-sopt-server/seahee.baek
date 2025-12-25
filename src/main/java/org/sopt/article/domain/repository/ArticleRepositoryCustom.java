package org.sopt.article.domain.repository;

import java.util.List;

import org.sopt.article.domain.entity.Article;

public interface ArticleRepositoryCustom {
	List<Article> search(Long cursor, int size, String searchWord, String memberName);
	List<Article> findNextArticles(Long cursor, int size);
}
