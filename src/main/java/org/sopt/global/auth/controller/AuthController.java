package org.sopt.global.auth.controller;

import java.time.Duration;

import org.sopt.common.response.SuccessResponse;
import org.sopt.global.auth.service.AuthService;
import org.sopt.global.auth.service.JwtService;
import org.sopt.member.presentation.dto.MemberResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final AuthService authService;
	private final JwtService jwtService;

	// @Operation(summary = "헤더 기반 Basic-Authentication")
	// @PostMapping("/v1/login")
	// public ResponseEntity<SuccessResponse<MemberResponseDTO>> login(
	// 	@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization
	// ) {
	// 	log.info(authorization);
	// 	MemberResponseDTO result = authService.login(authorization);
	// 	return ResponseEntity.ok()
	// 		.body(SuccessResponse.of(200, "로그인 성공!", result));
	// }
	//
	// @Operation(summary = "이메일/비밀번호 기반 로그인 (쿠키 발급)")
	// @PostMapping("/v2/login")
	// public ResponseEntity<SuccessResponse<MemberResponseDTO>> loginV2(
	// 	@RequestParam("email") String email,
	// 	@RequestParam("password") String password
	// ) {
	// 	MemberResponseDTO result = authService.loginWithCredentials(email, password);
	//
	// 	String credentials = email + ":" + password;
	//
	// 	ResponseCookie cookie = ResponseCookie.from("basic", credentials)
	// 		.httpOnly(true)
	// 		.secure(true)
	// 		.sameSite("Lax")
	// 		.maxAge(Duration.ofHours(1))
	// 		.path("/")
	// 		.build();
	//
	// 	return ResponseEntity.ok()
	// 		.header(HttpHeaders.SET_COOKIE, cookie.toString())
	// 		.body(SuccessResponse.of(200, "로그인 성공!", result));
	// }
	//
	// @Operation(summary = "쿠키로 로그인 여부 확인")
	// @GetMapping("/v2/me")
	// public ResponseEntity<SuccessResponse<MemberResponseDTO>> checkSession(
	// 	@CookieValue(value = "basic", required = false) String basicCookie
	// ) {
	// 	if (basicCookie == null) {
	// 		throw new IllegalArgumentException("로그인 쿠키가 없습니다.");
	// 	}
	//
	// 	int idx = basicCookie.indexOf(":");
	// 	if (idx < 0) {
	// 		throw new IllegalArgumentException("쿠키에 이메일과 비밀번호 구분자(:)가 없습니다.");
	// 	}
	// 	String email = basicCookie.substring(0, idx);
	// 	String password = basicCookie.substring(idx + 1);
	//
	// 	log.info("[v2] 세션 확인, 이메일: {}", email);
	// 	MemberResponseDTO result = authService.loginWithCredentials(email, password);
	// 	return ResponseEntity.ok(SuccessResponse.of(200, "내 정보 조회 성공!", result));
	// }
	//
	// @Operation(summary = "세션 기반 로그인 (JSESSIONID 발급)")
	// @PostMapping("/v3/login")
	// public ResponseEntity<SuccessResponse<MemberResponseDTO>> loginV3(
	// 	@RequestParam("email") String email,
	// 	@RequestParam("password") String password,
	// 	HttpSession session
	// ) {
	// 	MemberResponseDTO result = authService.loginWithCredentials(email, password);
	// 	session.setAttribute("LOGIN_MEMBER_ID", result.id());
	// 	return ResponseEntity.ok(SuccessResponse.of(200, "로그인 성공!", result));
	// }
	//
	// @Operation(summary = "세션 로그인 여부 확인")
	// @GetMapping("/v3/me")
	// public ResponseEntity<SuccessResponse<MemberResponseDTO>> checkSessionV3(
	// 	HttpSession session
	// ) {
	// 	Long memberId = (Long) session.getAttribute("LOGIN_MEMBER_ID");
	// 	MemberResponseDTO result = authService.getMemberById(memberId);
	// 	return ResponseEntity.ok(SuccessResponse.of(200,"로그인 성공!", result));
	// }

	@Operation(summary = "JWT 기반 로그인 (토큰 반환)")
	@PostMapping("/login")
	public ResponseEntity<SuccessResponse<String>> loginV4(
		@RequestParam("email") String email,
		@RequestParam("password") String password
	) {
		MemberResponseDTO member = authService.loginWithCredentials(email, password);
		String token = jwtService.generateToken(member.id(), member.email());
		return ResponseEntity.ok(SuccessResponse.of(200, "로그인 성공", token));
	}

	@Operation(summary = "JWT 검증 (Authorization: Bearer)")
	@GetMapping("/me")
	public ResponseEntity<SuccessResponse<MemberResponseDTO>> checkSessionV4() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long memberId = (Long) auth.getPrincipal(); // 우리가 principal에 memberId 넣었음

		MemberResponseDTO member = authService.getMemberById(memberId);
		return ResponseEntity.ok(SuccessResponse.of(200, "로그인 성공", member));
	}
}


