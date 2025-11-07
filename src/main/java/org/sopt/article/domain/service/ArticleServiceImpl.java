package org.sopt.article.domain.service;

import java.util.List;

import org.sopt.article.presentation.dto.ArticleAllInfoResponse;
import org.sopt.article.presentation.dto.ArticleCreateRequest;
import org.sopt.article.presentation.dto.ArticleInfoResponse;
import org.sopt.article.presentation.dto.SearchedArticleInfoResponse;
import org.sopt.article.presentation.dto.SearchedArticlesResponse;
import org.sopt.article.domain.entity.Article;
import org.sopt.member.domain.entity.Member;
import org.sopt.common.exception.MyException;
import org.sopt.common.exception.code.ArticleErrorCode;
import org.sopt.article.domain.repository.ArticleRepository;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public ArticleInfoResponse findById(Long id) {
		Article article = articleRepository.findById(id)
			.orElseThrow(() -> new MyException(ArticleErrorCode.ARTICLE_NOT_FOUND));
		return articleInfoGenerator(article);
	}

	public ArticleAllInfoResponse findAll() {
		List<Article> articleList = articleRepository.findAll();
		List<ArticleInfoResponse> response = articleList.stream()
			.map(this::articleInfoGenerator)
			.toList();

		return new ArticleAllInfoResponse(response);
	}

	public SearchedArticlesResponse search(String searchWord, String memberName, int page, int size) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
		Page<Article> result = articleRepository.search(searchWord, memberName, pageable);

		List<SearchedArticleInfoResponse> articles = result.getContent().stream()
			.map(a -> new SearchedArticleInfoResponse(
				a.getId(),
				a.getTitle(),
				a.getCreatedAt(),
				a.getMember().getId(),
				a.getMember().getName()
			))
			.toList();

		return new SearchedArticlesResponse(
			articles,
			result.getNumber(),
			result.getTotalPages(),
			result.getTotalElements()
		);
	}

	private ArticleInfoResponse articleInfoGenerator(Article article) {
		return ArticleInfoResponse.of(
			article.getId(),
			article.getTitle(),
			article.getContent(),
			article.getTag(),
			article.getCreatedAt(),
			article.getMember().getId()
		);
	}
}
