package org.sopt.member.presentation;

import static org.sopt.member.domain.entity.MemberInputValidator.*;

import org.sopt.common.response.SuccessResponse;
import org.sopt.member.presentation.dto.MemberAllInfoResponse;
import org.sopt.member.presentation.dto.MemberCreateRequest;
import org.sopt.member.presentation.dto.MemberInfoResponse;

import org.sopt.common.exception.code.MemberSuccessCode;
import org.sopt.member.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class MemberController {

	@Autowired
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ResponseEntity<SuccessResponse<MemberInfoResponse>> createMember(
		@RequestBody MemberCreateRequest memberCreateRequest) {

		nonEmptyChecker(memberCreateRequest.name());
		validAgeChecker(memberCreateRequest.birthday());
		validEmailChecker(memberCreateRequest.email());

		MemberInfoResponse response = memberService.join(memberCreateRequest);
		System.out.println(memberCreateRequest);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(MemberSuccessCode.MEMBER_CREATE_SUCCESS, response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<MemberInfoResponse>> findMemberById(
		@PathVariable  Long id) {
		MemberInfoResponse response = memberService.getMemberInfoResponse(id);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(MemberSuccessCode.MEMBER_GET_SUCCESS, response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberAllInfoResponse>> getAllMembers() {
		MemberAllInfoResponse response = memberService.findAllMembers();
		return ResponseEntity.ok()
			.body(SuccessResponse.of(MemberSuccessCode.MEMBER_ALL_GET_SUCCESS, response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<Void>> deleteMember(
		@PathVariable Long id) {
		memberService.deleteMemberById(id);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(MemberSuccessCode.MEMBER_DELETE_SUCCESS));
	}

}