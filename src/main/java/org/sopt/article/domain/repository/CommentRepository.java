package org.sopt.article.domain.repository;

import java.util.List;

import org.sopt.article.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByArticleId(Long articleId);
	Long findMemberIdById(Long commentId);
}
