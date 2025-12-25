package org.sopt.article.domain.repository;

import java.util.List;

import org.sopt.article.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
	@Query(value = """
        SELECT a.*
        FROM article a
        JOIN member m ON a.member_Id = m.id
        WHERE (:cursor IS NULL OR a.id < :cursor)
          AND (:memberName IS NULL OR :memberName = '' OR MATCH(m.name) AGAINST(:memberName IN BOOLEAN MODE))
          AND (:searchWord IS NULL OR :searchWord = '' OR MATCH(a.title) AGAINST(:searchWord IN BOOLEAN MODE))
        ORDER BY a.id DESC
        LIMIT :limit
        """, nativeQuery = true)
	List<Article> searchByCursorFullText(
		@Param("cursor") Long cursor,
		@Param("limit") int limit,
		@Param("searchWord") String searchWord,
		@Param("memberName") String memberName
	);
}
