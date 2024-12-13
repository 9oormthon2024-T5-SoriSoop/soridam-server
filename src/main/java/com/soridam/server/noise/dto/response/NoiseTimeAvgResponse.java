package com.soridam.server.noise.dto.response;

import java.time.LocalDateTime;

import com.soridam.server.noise.domain.Noise;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseTimeAvgResponse(
	@NotNull
	int avgDecibel,

	@NotNull
	LocalDateTime createdAt
) {
	public static NoiseTimeAvgResponse from(Noise noise) {
		return NoiseTimeAvgResponse.builder()
			.avgDecibel(noise.getAvgDecibel())
			.createdAt(noise.getCreatedAt())
			.build();
	}
}
