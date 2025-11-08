package org.sopt.member.presentation.dto;

import java.util.List;

public record MemberAllInfoResponse(
	List<MemberInfoResponse> memberList
) {
}
