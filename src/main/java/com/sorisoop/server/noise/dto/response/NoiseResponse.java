package com.sorisoop.server.noise.dto.response;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseResponse(
	@NotNull
	int averageDecibel,

	@NotNull
	LocalDateTime createdAt
) {
	public static NoiseResponse of(int averageDecibel, LocalDateTime createdAt) {
		return NoiseResponse.builder()
			.averageDecibel(averageDecibel)
			.createdAt(createdAt)
			.build();
	}
}
