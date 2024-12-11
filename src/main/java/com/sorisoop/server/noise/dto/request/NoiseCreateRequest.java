package com.sorisoop.server.noise.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record NoiseCreateRequest(
	@NotNull
	@Positive
	int averageDecibel,

	@NotNull
	@Positive
	int maximumDecibel,

	@NotNull
	@Positive
	int minimumDecibel,

	@NotNull
	@Positive
	int measurementTime,

	@NotBlank
	String review
) {
}
