package org.sopt.global.auth.resolver;

import org.sopt.global.auth.annotation.CurrentMember;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentMemberArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentMember.class)
			&& Long.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth.getPrincipal() == null) {
			throw new IllegalArgumentException("토큰을 포함하여 요청해주세요.");
		}

		Object principal = auth.getPrincipal();
		if (principal instanceof Long memberId) {
			return memberId;
		}

		throw new IllegalArgumentException("인증 정보가 올바르지 않습니다.");
	}
}
