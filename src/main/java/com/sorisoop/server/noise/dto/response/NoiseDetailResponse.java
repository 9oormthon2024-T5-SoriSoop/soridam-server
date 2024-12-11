package com.sorisoop.server.noise.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseDetailResponse(
	@NotNull
	int avgDecibel,

	@NotNull
	int maxDecibel,

	@NotNull
	List<NoiseReviewResponse> reviews
) {
	public static NoiseDetailResponse of(int avgDecibel, int maxDecibel, List<NoiseReviewResponse> reviews) {
		return NoiseDetailResponse.builder()
			.avgDecibel(avgDecibel)
			.maxDecibel(maxDecibel)
			.reviews(reviews)
			.build();
	}
}
