package org.sopt.article.domain.service;

import org.sopt.article.presentation.dto.CommentRequest;
import org.sopt.article.presentation.dto.CommentResponse;

public interface CommentService {
	CommentResponse createComment(CommentRequest request, Long articleId, Long memberId);
}
