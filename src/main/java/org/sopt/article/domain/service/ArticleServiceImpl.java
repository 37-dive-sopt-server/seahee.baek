package org.sopt.article.domain.service;

import java.util.List;

import org.sopt.article.presentation.dto.ArticleAllInfoResponse;
import org.sopt.article.presentation.dto.ArticleCreateRequest;
import org.sopt.article.presentation.dto.ArticleInfoResponse;
import org.sopt.article.presentation.dto.CursorArticleAllInfoResponse;

import org.sopt.article.domain.entity.Article;
import org.sopt.member.domain.entity.Member;
import org.sopt.common.exception.MyException;
import org.sopt.article.exception.code.ArticleErrorCode;
import org.sopt.article.domain.repository.ArticleRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleInfoResponse createArticle(ArticleCreateRequest request, Member member) {

		Article article = Article.create(request.title(), request.content(), member, request.tag());

		try {
			articleRepository.save(article);
		} catch (DataIntegrityViolationException e) {
			throw new MyException(ArticleErrorCode.ARTICLE_TITLE_DUPLICATED);
		}

		return articleInfoGenerator(article);
	}

	@Cacheable(
		cacheNames = "article:detail",
		key = "#id"
	)
	public ArticleInfoResponse findById(Long id) {
		Article article = articleRepository.findById(id)
			.orElseThrow(() -> new MyException(ArticleErrorCode.ARTICLE_NOT_FOUND));
		return articleInfoGenerator(article);
	}

	@Cacheable(
		cacheNames = "article:all",
		key = "T(java.util.Objects).toString(#cursor, 'first') + ':' + #size"
	)
	public CursorArticleAllInfoResponse findAll(Long cursor, int size) {

		List<Article> articleList = articleRepository.findNextArticles(cursor, size);
		return cursorArticleAllInfoResponseGenerator(size, articleList);
	}

	@Cacheable(
		cacheNames = "article:search",
		key =
			"T(java.util.Objects).toString(#cursor, 'first')"
				+ " + ':' + #size"
				+ " + ':' + T(java.util.Objects).toString(#searchWord, 'none')"
				+ " + ':' + T(java.util.Objects).toString(#memberName, 'none')"
	)
	public CursorArticleAllInfoResponse search(Long cursor, int size, String searchWord, String memberName) {

		List<Article> articleList = articleRepository.search(cursor, size, searchWord, memberName);
		return cursorArticleAllInfoResponseGenerator(size, articleList);
	}

	private ArticleInfoResponse articleInfoGenerator(Article article) {
		return ArticleInfoResponse.of(
			article.getId(),
			article.getTitle(),
			article.getContent(),
			article.getTag(),
			article.getCreatedAt(),
			article.getMember().getId(),
			article.getMember().getName(),
			article.getComments()
		);
	}

	private CursorArticleAllInfoResponse cursorArticleAllInfoResponseGenerator(int size, List<Article> articleList) {

		boolean hasNext = articleList.size() > size;
		if (hasNext) {
			articleList = articleList.subList(0, size);
		}

		List<ArticleAllInfoResponse> response = articleList.stream()
			.map(a -> ArticleAllInfoResponse.of(
				a.getId(),
				a.getTitle(),
				a.getTag(),
				a.getCreatedAt(),
				a.getMember().getId(),
				a.getMember().getName()
			))
			.toList();

		Long nextCursor = response.isEmpty() ? null : response.get(response.size() - 1).id();

		return new CursorArticleAllInfoResponse(response, nextCursor, hasNext);
	}

}
