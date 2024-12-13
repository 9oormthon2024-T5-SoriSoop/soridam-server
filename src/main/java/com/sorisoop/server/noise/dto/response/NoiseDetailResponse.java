package com.sorisoop.server.noise.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseDetailResponse(
	@NotNull
	List<NoiseResponse> noises,

	@NotNull
	List<NoiseReviewResponse> reviews
) {
	public static NoiseDetailResponse of(List<NoiseResponse> noises, List<NoiseReviewResponse> reviews) {
		return NoiseDetailResponse.builder()
			.noises(noises)
			.reviews(reviews)
			.build();
	}
}
