package com.sorisoop.server.noise.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseSummaryListResponse(
	// 주변 추천 시, 내 데이터 기록
	@NotNull
	List<NoiseSummaryResponse> responses
) {
	public static NoiseSummaryListResponse of(List<NoiseSummaryResponse> responses, List<String> reviews) {
		return NoiseSummaryListResponse.builder()
			.responses(responses)
			.build();
	}
}
