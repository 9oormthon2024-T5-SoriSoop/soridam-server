package com.sorisoop.server.noise.dto.response;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseListResponse(
	@NotNull
	List<NoiseResponse> responses,

	@NotNull
	List<String> reviews
) {
	public static NoiseListResponse of(List<NoiseResponse> responses, List<String> reviews) {
		return NoiseListResponse.builder()
			.responses(responses)
			.reviews(reviews)
			.build();
	}
}
