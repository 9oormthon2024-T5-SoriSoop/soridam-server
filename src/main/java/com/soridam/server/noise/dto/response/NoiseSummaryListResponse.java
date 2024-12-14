package com.soridam.server.noise.dto.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record NoiseSummaryListResponse(
	@Schema(description = "소음 요약 정보 목록", requiredMode = REQUIRED)
	List<NoiseSummaryResponse> responses
) {
	public static NoiseSummaryListResponse of(List<NoiseSummaryResponse> responses) {
		return NoiseSummaryListResponse.builder()
			.responses(responses)
			.build();
	}
}
