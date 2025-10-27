package org.sopt.controller.member;

import static org.sopt.util.validator.MemberInputValidator.*;

import org.sopt.common.response.SuccessResponse;
import org.sopt.controller.member.dto.MemberAllInfoResponse;
import org.sopt.controller.member.dto.MemberCreateRequest;
import org.sopt.controller.member.dto.MemberInfoResponse;

import org.sopt.exception.code.MemberSuccessCode;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/users")
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

	@GetMapping("/users/{id}")
	public ResponseEntity<SuccessResponse<MemberInfoResponse>> findMemberById(
		@PathVariable  Long id) {
		MemberInfoResponse response = memberService.getMemberInfoResponse(id);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(MemberSuccessCode.MEMBER_GET_SUCCESS, response));
	}

	@GetMapping("/users")
	public ResponseEntity<SuccessResponse<MemberAllInfoResponse>> getAllMembers() {
		MemberAllInfoResponse response = memberService.findAllMembers();
		return ResponseEntity.ok()
			.body(SuccessResponse.of(MemberSuccessCode.MEMBER_ALL_GET_SUCCESS, response));
	}

	@DeleteMapping("/users")
	public ResponseEntity<SuccessResponse<Void>> deleteMember(Long id) {
		memberService.deleteMemberById(id);
		return ResponseEntity.ok()
			.body(SuccessResponse.of(MemberSuccessCode.MEMBER_DELETE_SUCCESS));
	}

}