package com.sorisoop.server.noise.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NoiseResponse(
	@NotNull
	double x,

	@NotNull
	double y,

	@NotNull
	double average
) {
	public static NoiseResponse of(double x, double y, double average) {
		return NoiseResponse.builder()
			.x(x)
			.y(y)
			.average(average)
			.build();
	}
}
