package com.soridam.server.noise.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record NoiseListResponse(
	List<NoiseResponse> noises
) {
	public static NoiseListResponse of(List<NoiseResponse> noises) {
		return NoiseListResponse.builder()
			.noises(noises)
			.build();
	}
}
