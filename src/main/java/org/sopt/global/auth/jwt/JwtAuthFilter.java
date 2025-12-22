package org.sopt.global.auth.jwt;

import java.io.IOException;
import java.util.Collections;

import org.sopt.global.auth.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	public JwtAuthFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return "/login".equals(request.getRequestURI());
	}

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {

		String auth = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (StringUtils.hasText(auth) && auth.startsWith("Bearer ")) {
			String token = auth.substring("Bearer ".length()).trim();
			try {
				Long memberId = jwtService.verifyAndGetMemberId(token);

				UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(memberId, null, Collections.emptyList());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}