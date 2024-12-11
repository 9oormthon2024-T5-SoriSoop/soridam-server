package com.sorisoop.server.noise.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseSearchResponse(
	@NotNull
	List<NoiseResponse> noises,

	@NotNull
	List<NoiseReviewResponse> reviews
) {
	public static NoiseSearchResponse of(List<NoiseResponse> noises, List<NoiseReviewResponse> reviews) {
		return NoiseSearchResponse.builder()
			.noises(noises)
			.reviews(reviews)
			.build();
	}
}
