package org.sopt.article.domain.service;

import org.sopt.article.domain.entity.Article;
import org.sopt.article.domain.entity.Comment;
import org.sopt.article.domain.repository.ArticleRepository;
import org.sopt.article.domain.repository.CommentRepository;
import org.sopt.article.exception.code.ArticleErrorCode;
import org.sopt.article.exception.code.CommentErrorCode;
import org.sopt.article.presentation.dto.CommentRequest;
import org.sopt.article.presentation.dto.CommentResponse;
import org.sopt.common.exception.MyException;
import org.sopt.member.domain.entity.Member;
import org.sopt.member.domain.repository.MemberRepository;
import org.sopt.member.exception.code.MemberErrorCode;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final ArticleRepository articleRepository;

	public CommentResponse createComment(CommentRequest request, Long articleId, Long memberId) {

		Article article = articleRepository.findById(articleId)
			.orElseThrow(()-> new MyException(ArticleErrorCode.ARTICLE_NOT_FOUND));
		Member member = memberRepository.findById(memberId)
			.orElseThrow(()-> new MyException(MemberErrorCode.MEMBER_NOT_FOUND));

		Comment comment = commentRepository.save(Comment.create(request.content(), article, member));
		return CommentResponse.of(comment.getId(), comment.getContent(), comment.getMember().getName());
	}

	public CommentResponse updateComment(CommentRequest request, Long commentId, Long memberId) {

		Comment comment = throwExceptionOrCommentReturner(commentId, memberId);

		comment.updateContent(request.content());
		return CommentResponse.of(comment.getId(), comment.getContent(), comment.getMember().getName());
	}

	public void deleteComment(Long commentId, Long memberId) {

		Comment comment = throwExceptionOrCommentReturner(commentId, memberId);
		commentRepository.delete(comment);
	}

	// 댓글 접근 권한을 체크하고 예외를 던지거나 comment 객체를 반환하는 메서드
	private Comment throwExceptionOrCommentReturner(Long commentId, Long memberId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(()-> new MyException(CommentErrorCode.COMMENT_NOT_FOUND));
		Long writerId = comment.getMember().getId();
		if (!writerId.equals(memberId)) {
			throw new MyException(CommentErrorCode.COMMENT_FORBIDDEN);
		}
		return comment;
	}

}
