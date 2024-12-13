package com.sorisoop.server.noise.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseSearchResponse(
	// 주변 추천 후 클릭 시
	@NotNull
	List<NoiseTimeAvgResponse> noises,

	@NotNull
	List<NoiseReviewResponse> reviews
) {
	public static NoiseSearchResponse of(List<NoiseTimeAvgResponse> noises, List<NoiseReviewResponse> reviews) {
		return NoiseSearchResponse.builder()
			.noises(noises)
			.reviews(reviews)
			.build();
	}
}
