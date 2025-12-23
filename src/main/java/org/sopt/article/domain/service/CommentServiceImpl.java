package org.sopt.article.domain.service;

import org.sopt.article.domain.entity.Article;
import org.sopt.article.domain.entity.Comment;
import org.sopt.article.domain.repository.ArticleRepository;
import org.sopt.article.domain.repository.CommentRepository;
import org.sopt.article.exception.code.ArticleErrorCode;
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

}
