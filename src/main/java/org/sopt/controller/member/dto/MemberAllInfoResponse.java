package org.sopt.controller.member.dto;

import java.util.List;

public record MemberAllInfoResponse(
	List<MemberInfoResponse> memberList
) {
}
