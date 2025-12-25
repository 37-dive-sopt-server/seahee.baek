package org.sopt.article.presentation.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.sopt.article.domain.entity.Comment;
import org.sopt.article.domain.entity.enums.Tag;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ArticleInfoResponse(
	Long id,
	String title,
	String content,
	Tag tag,

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createdAt,

	Long memberId,
	String memberName,
	List<CommentResponse> comments
) {

	public static ArticleInfoResponse of(
		Long id,
		String title,
		String content,
		Tag tag,
		LocalDateTime createdAt,
		Long memberId,
		String memberName,
		List<Comment> comments
	) {
		List<CommentResponse> commentResponses = comments == null ? Collections.emptyList() : comments.stream()
			.map(c -> CommentResponse.of(
				c.getId(),
				c.getContent(),
				c.getMember().getName(),
				c.getCreatedAt()
			))
			.toList();
		return new ArticleInfoResponse(id, title, content, tag, createdAt, memberId, memberName, commentResponses);
	}
}
