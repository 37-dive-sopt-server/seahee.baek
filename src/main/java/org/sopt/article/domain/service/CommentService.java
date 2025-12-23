package org.sopt.article.domain.service;

import org.sopt.article.presentation.dto.CommentCreateRequest;
import org.sopt.article.presentation.dto.CommentCreateResponse;

public interface CommentService {
	CommentCreateResponse createComment(CommentCreateRequest request, Long articleId, Long memberId);
}
