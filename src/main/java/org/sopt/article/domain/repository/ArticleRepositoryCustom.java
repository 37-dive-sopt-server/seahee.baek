package org.sopt.article.domain.repository;

import org.sopt.article.domain.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
	Page<Article> search(String searchWord, String memberName, Pageable pageable);
}
